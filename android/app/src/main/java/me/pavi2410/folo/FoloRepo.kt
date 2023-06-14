package me.pavi2410.folo

import kotlinx.coroutines.delay
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetInfo


val testData = listOf(
    FoloProfile(
        platform = FoloPlatform.Twitter,
        username = "PavitraGolchha",
        followers = 296
    ),
    FoloProfile(
        platform = FoloPlatform.Youtube,
        username = "PavitraGolchha",
        followers = 1_000_000_000
    )
)

object FoloRepo {
    suspend fun fetchData(username: String): FoloWidgetInfo {
//        val client = HttpClient(CIO)
//        val response = client.get("https://ktor.io/")
//        return response.body<FoloWidgetInfo.Available>()

        delay(100)

        return FoloWidgetInfo.Available(testData.first())
    }
}

