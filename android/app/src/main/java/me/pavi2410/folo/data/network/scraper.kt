package me.pavi2410.folo.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile

@Serializable
data class FoloProfileResponse(
    val platform: String,
    val username: String,
    val metrics: Map<String, Long>,
    val updatedAt: Long,
)

suspend fun fetchProfile(platform: FoloPlatform, username: String): FoloProfileResponse {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(HttpCache)
    }

    val response: HttpResponse = client.get("https://api.folo.pavi2410.me/profile") {
        url {
            parameters.append("platform", platform.toString().lowercase())
            parameters.append("username", username)
        }
    }

    return response.body()
}