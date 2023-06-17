package me.pavi2410.folo

import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetInfo

class FoloRepo(private val database: FoloDatabase) {
    fun getAllProfiles() =
        database.foloProfileQueries.selectAll().executeAsList().map {
            FoloProfile(
                it.platform,
                it.username,
                it.followers
            )
        }

    fun addProfile(platform: FoloPlatform, username: String, followers: Long) {
        database.foloProfileQueries.insertProfile(platform, username, followers)
    }

    fun fetchWidgetInfo(): FoloWidgetInfo {
        val profile = database.foloProfileQueries.selectAll().executeAsList().firstOrNull()
            ?: return FoloWidgetInfo.Unavailable("Profile not available")

        return FoloWidgetInfo.Available(
            FoloProfile(
                profile.platform,
                profile.username,
                profile.followers
            )
        )
    }
}
