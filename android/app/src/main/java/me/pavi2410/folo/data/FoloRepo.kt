package me.pavi2410.folo.data

import android.util.Log
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import me.pavi2410.folo.FoloDatabase
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetInfo

class FoloRepo(private val database: FoloDatabase) {
    val db = Firebase.firestore
    val auth = Firebase.auth

    suspend fun getAllProfiles() =
        getProfileRefs().map {
            getProfile(it)
        }

    suspend fun addProfile(platform: FoloPlatform, username: String, platformMetric: String) {
        // todo:
        //  - make sure username is not empty
        //  - check if profile already exists
        //  - check if username exists in the platform
        //  - fetch followers count

        require(username.isNotBlank()) { "Username cannot be blank!" }

        val profileRes = fetchProfile(platform, username)

        database.foloProfileQueries.insertProfile(
            platform,
            username,
            profileRes.metrics[platformMetric] ?: 0
        )
    }

    suspend fun deleteProfile(profileId: String) {
        val currentUser = auth.currentUser ?: return

        db.collection("users")
            .document(currentUser.uid)
            .update(
                "profiles",
                FieldValue.arrayRemove(
                    db.collection("profiles").document(profileId)
                )
            )
            .await()
    }

    fun fetchWidgetInfo(appWidgetId: Int): FoloWidgetInfo {
        val profile = database.foloProfileQueries.selectAll().executeAsList().firstOrNull()
            ?: return FoloWidgetInfo.Unavailable("Profile not available")

        return FoloWidgetInfo.Available(
            FoloProfile(
                "999",
                profile.platform,
                profile.username,
                profile.followers
            )
        )
    }

    fun upsertWidget(appWidgetId: Int, profileId: String) {
        database.foloWidgetQueries.insertWidget(appWidgetId.toLong(), profileId)
    }

    suspend fun getProfileRefs(): List<DocumentReference> {
        val currentUser = auth.currentUser ?: return emptyList()

        Log.d("GetProfileRefs", currentUser.uid)
        val userDoc = db.collection("users")
            .document(currentUser.uid)
            .get()
            .await()

        if (!userDoc.exists()) {
            Log.d("GetProfileRefs", "User does not exist")
            return emptyList()
        }

        val userProfilesRef = userDoc.get("profiles")
        if (userProfilesRef == null) {
            Log.d("GetProfileRefs", "User does not have any profiles")
            return emptyList()
        }

        return userProfilesRef as List<DocumentReference>
    }

    suspend fun getProfile(profileRef: DocumentReference): FoloProfile {
        val profileDoc = profileRef.get().await()
        Log.d("GetProfile", profileDoc.id)
        Log.d("GetProfile", profileDoc.data.toString())

        val profileHistoryDoc = profileRef.collection("history")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()

        Log.d("GetProfile", profileHistoryDoc.documents.toString())

        val latestMetricValue = profileHistoryDoc.documents.firstOrNull()

        return FoloProfile(
            id = profileDoc.id,
            platform = FoloPlatform.valueOf(profileDoc["platform"] as String),
            username = profileDoc["username"] as String,
            followers = (latestMetricValue?.get("value") ?: 0L) as Long,
        )
    }
}
