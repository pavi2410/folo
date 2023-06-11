package me.pavi2410.folo.models

data class FoloProfile(
        val platform: FoloPlatform,
        val username: String,
        val displayName: String,
        val followers: Long
)
