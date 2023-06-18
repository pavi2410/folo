package me.pavi2410.folo.ui

import androidx.compose.ui.graphics.Color
import compose.icons.FeatherIcons
import compose.icons.feathericons.Twitter
import compose.icons.feathericons.Youtube
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile

fun profileLink(data: FoloProfile) = when (data.platform) {
    FoloPlatform.Twitter -> "https://twitter.com/${data.username}"
    FoloPlatform.Youtube -> "https://youtube.com/c/${data.username}"
}

fun backgroundGradient(data: FoloProfile) = when (data.platform) {
    FoloPlatform.Twitter -> listOf(Color(0xff3B82F6), Color(0xff2563EB))
    FoloPlatform.Youtube -> listOf(Color(0xffEF4444), Color(0xffDC2626))
}

fun followersText(data: FoloProfile) = when (data.platform) {
    FoloPlatform.Twitter -> "followers"
    FoloPlatform.Youtube -> "subscribers"
}

fun platformIcon(data: FoloPlatform) = when (data) {
    FoloPlatform.Twitter -> FeatherIcons.Twitter
    FoloPlatform.Youtube -> FeatherIcons.Youtube
}