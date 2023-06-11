package me.pavi2410.folo.widgets

import me.pavi2410.folo.models.FoloProfile

sealed interface FoloWidgetInfo {
    object Loading : FoloWidgetInfo

    data class Available(
            val profile: FoloProfile
    ) : FoloWidgetInfo

    data class Unavailable(val message: String) : FoloWidgetInfo
}