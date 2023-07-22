package me.pavi2410.folo.ui

import androidx.compose.ui.graphics.Color
import me.pavi2410.folo.R
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.ui.theme.TailwindColors

fun profileLink(data: FoloProfile) = when (data.platform) {
    FoloPlatform.github -> "https://github.com/${data.username}"
    FoloPlatform.twitter -> "https://twitter.com/${data.username}"
    FoloPlatform.youtube -> "https://youtube.com/@${data.username}"
    FoloPlatform.threads -> "https://threads.net/@${data.username}"
}

fun backgroundGradient(platform: FoloPlatform) = when (platform) {
    FoloPlatform.github -> listOf(
        Color(TailwindColors.stone700),
        Color(TailwindColors.stone900)
    )

    FoloPlatform.threads -> listOf(
        Color(TailwindColors.zinc800),
        Color(TailwindColors.zinc900)
    )

    FoloPlatform.twitter -> listOf(
        Color(TailwindColors.blue500),
        Color(TailwindColors.blue600)
    )

    FoloPlatform.youtube -> listOf(
        Color(TailwindColors.red500),
        Color(TailwindColors.red600)
    )
}

fun gradientDrawable(platform: FoloPlatform) = when (platform) {
    FoloPlatform.github -> R.drawable.github_gradient
    FoloPlatform.threads -> R.drawable.github_gradient
    FoloPlatform.twitter -> R.drawable.twitter_gradient
    FoloPlatform.youtube -> R.drawable.youtube_gradient
}

fun followersText(platform: FoloPlatform) = when (platform) {
    FoloPlatform.youtube -> "subscribers"
    else -> "followers"
}

fun platformMetrics(platform: FoloPlatform) = when (platform) {
    FoloPlatform.github -> listOf("followers")
    FoloPlatform.threads -> listOf("followers")
    FoloPlatform.twitter -> listOf("followers", "subscribers")
    FoloPlatform.youtube -> listOf("subscribers")
}

fun platformIcon(data: FoloPlatform) = when (data) {
    FoloPlatform.github -> R.drawable.brand_github
    FoloPlatform.threads -> R.drawable.brand_threads
    FoloPlatform.twitter -> R.drawable.brand_twitter
    FoloPlatform.youtube -> R.drawable.brand_youtube
}