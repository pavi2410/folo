package me.pavi2410.folo.models

import kotlinx.serialization.Serializable

@Serializable
data class FoloProfile(
    val id: String,
    val platform: FoloPlatform,
    val username: String,
    val followers: Long
)
