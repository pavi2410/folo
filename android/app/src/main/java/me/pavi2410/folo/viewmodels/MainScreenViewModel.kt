package me.pavi2410.folo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile

class MainScreenViewModel : ViewModel() {
    val db = Firebase.firestore
    val auth = Firebase.auth

    init {
        viewModelScope.launch {
            auth.signInAnonymously().await()
        }
    }

    suspend fun getProfileRefs(): List<DocumentReference> {
        auth.signInAnonymously().await()
        val currentUser = auth.currentUser ?: return emptyList()

        Log.d("GetProfileRefs", currentUser.uid)
        val userDoc = db.collection("users")
            .document(currentUser.uid)
            .get().await()

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
            .get().await()

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