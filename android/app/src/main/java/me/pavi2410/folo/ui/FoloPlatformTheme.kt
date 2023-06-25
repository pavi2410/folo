package me.pavi2410.folo.ui

import androidx.compose.ui.graphics.Color
import compose.icons.FeatherIcons
import compose.icons.feathericons.Github
import compose.icons.feathericons.Twitter
import compose.icons.feathericons.Youtube
import me.pavi2410.folo.R
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.ui.theme.TailwindColors

fun profileLink(data: FoloProfile) = when (data.platform) {
    FoloPlatform.Github -> "https://github.com/${data.username}"
    FoloPlatform.Twitter -> "https://twitter.com/${data.username}"
    FoloPlatform.Youtube -> "https://youtube.com/c/${data.username}"
}

fun backgroundGradient(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Github -> listOf(
        Color(TailwindColors.neutral800),
        Color(TailwindColors.neutral900)
    )

    FoloPlatform.Twitter -> listOf(
        Color(TailwindColors.blue500),
        Color(TailwindColors.blue600)
    )

    FoloPlatform.Youtube -> listOf(
        Color(TailwindColors.red500),
        Color(TailwindColors.red600)
    )
}

fun gradientDrawable(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Github -> R.drawable.github_gradient
    FoloPlatform.Twitter -> R.drawable.twitter_gradient
    FoloPlatform.Youtube -> R.drawable.youtube_gradient
}

fun followersText(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Github, FoloPlatform.Twitter -> "followers"
    FoloPlatform.Youtube -> "subscribers"
}

fun platformIcon(data: FoloPlatform) = when (data) {
    FoloPlatform.Github -> FeatherIcons.Github
    FoloPlatform.Twitter -> FeatherIcons.Twitter
    FoloPlatform.Youtube -> FeatherIcons.Youtube
}