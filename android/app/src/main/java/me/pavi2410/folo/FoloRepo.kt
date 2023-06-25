package me.pavi2410.folo

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import me.pavi2410.folo.data.network.fetchProfile
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetInfo

class FoloRepo(private val database: FoloDatabase) {
    fun getAllProfiles() =
        database.foloProfileQueries.selectAll(::FoloProfile)
            .asFlow()
            .mapToList(Dispatchers.IO)

    suspend fun addProfile(platform: FoloPlatform, username: String) {
        // todo:
        //  - make sure username is not empty
        //  - check if profile already exists
        //  - check if username exists in the platform
        //  - fetch followers count

        require(username.isNotBlank()) { "Username cannot be blank!" }

        val profileRes = fetchProfile(platform, username)

        database.foloProfileQueries.insertProfile(platform, username, profileRes.followers)
    }

    fun deleteProfile(profileId: Long) {
        database.foloProfileQueries.deleteProfile(profileId)
    }

    fun fetchWidgetInfo(appWidgetId: Int): FoloWidgetInfo {
        val profile = database.foloProfileQueries.selectAll().executeAsList().firstOrNull()
            ?: return FoloWidgetInfo.Unavailable("Profile not available")

        return FoloWidgetInfo.Available(
            FoloProfile(
                999,
                profile.platform,
                profile.username,
                profile.followers
            )
        )
    }

    fun upsertWidget(appWidgetId: Int, profileId: Long) {
        database.foloWidgetQueries.insertWidget(appWidgetId.toLong(), profileId)
    }
}
