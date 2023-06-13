package me.pavi2410.folo.models

import kotlinx.serialization.Serializable

@Serializable
data class FoloProfile(
        val platform: FoloPlatform,
        val username: String,
        val displayName: String,
        val followers: Long
)
