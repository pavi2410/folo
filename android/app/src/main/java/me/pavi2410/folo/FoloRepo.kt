package me.pavi2410.folo

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetInfo

class FoloRepo(private val database: FoloDatabase) {
    fun getAllProfiles() =
        database.foloProfileQueries.selectAll(::FoloProfile)
            .asFlow()
            .mapToList(Dispatchers.IO)

    fun addProfile(platform: FoloPlatform, username: String, followers: Long) {
        database.foloProfileQueries.insertProfile(platform, username, followers)
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
