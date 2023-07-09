package me.pavi2410.folo.ui

import androidx.compose.ui.graphics.Color
import compose.icons.TablerIcons
import compose.icons.tablericons.BrandGithub
import compose.icons.tablericons.BrandTwitter
import compose.icons.tablericons.BrandYoutube
import compose.icons.tablericons.HeartBroken
import me.pavi2410.folo.R
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.ui.theme.TailwindColors

fun profileLink(data: FoloProfile) = when (data.platform) {
    FoloPlatform.Github -> "https://github.com/${data.username}"
    FoloPlatform.Twitter -> "https://twitter.com/${data.username}"
    FoloPlatform.Youtube -> "https://youtube.com/@${data.username}"
    FoloPlatform.Threads -> "https://threads.net/@${data.username}"
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

    FoloPlatform.Threads -> listOf(
        Color(TailwindColors.neutral800),
        Color(TailwindColors.neutral900)
    )
}

fun gradientDrawable(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Github -> R.drawable.github_gradient
    FoloPlatform.Twitter -> R.drawable.twitter_gradient
    FoloPlatform.Youtube -> R.drawable.youtube_gradient
    FoloPlatform.Threads -> R.drawable.github_gradient
}

fun followersText(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Youtube -> "subscribers"
    else -> "followers"
}

fun platformMetrics(platform: FoloPlatform) = when (platform) {
    FoloPlatform.Github -> listOf("followers")
    FoloPlatform.Threads -> listOf("followers")
    FoloPlatform.Twitter -> listOf("followers", "subscribers")
    FoloPlatform.Youtube -> listOf("subscribers")
}

fun platformIcon(data: FoloPlatform) = when (data) {
    FoloPlatform.Github -> TablerIcons.BrandGithub
    FoloPlatform.Twitter -> TablerIcons.BrandTwitter
    FoloPlatform.Youtube -> TablerIcons.BrandYoutube
    FoloPlatform.Threads -> TablerIcons.HeartBroken // TODO: Replace this icon when available
}