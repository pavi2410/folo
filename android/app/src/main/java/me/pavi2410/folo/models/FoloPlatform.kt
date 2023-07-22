package me.pavi2410.folo.models

import kotlinx.serialization.Serializable

@Suppress("EnumEntryName")
@Serializable
enum class FoloPlatform {
    github,
    threads,
    twitter,
    youtube
}