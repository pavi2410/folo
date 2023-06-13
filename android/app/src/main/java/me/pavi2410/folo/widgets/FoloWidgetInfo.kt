package me.pavi2410.folo.widgets

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.pavi2410.folo.models.FoloProfile
import java.io.InputStream
import java.io.OutputStream

@Serializable
sealed interface FoloWidgetInfo {
    @Serializable
    object Loading : FoloWidgetInfo

    @Serializable
    data class Available(
        val profile: FoloProfile
    ) : FoloWidgetInfo

    @Serializable
    data class Unavailable(val message: String) : FoloWidgetInfo
}

object FoloWidgetInfoSerializer : Serializer<FoloWidgetInfo> {
    override val defaultValue = FoloWidgetInfo.Unavailable("no place found")

    override suspend fun readFrom(input: InputStream): FoloWidgetInfo = try {
        Json.decodeFromString<FoloWidgetInfo>(
            input.readBytes().decodeToString()
        )
    } catch (exception: SerializationException) {
        throw CorruptionException("Could not read weather data: ${exception.message}")
    }

    override suspend fun writeTo(t: FoloWidgetInfo, output: OutputStream) {
        output.use {
            it.write(
                Json.encodeToString(t).toByteArray()
            )
        }
    }
}