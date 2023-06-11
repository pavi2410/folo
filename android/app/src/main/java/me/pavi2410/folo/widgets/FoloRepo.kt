package me.pavi2410.folo.widgets

import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile

object FoloRepo {
    suspend fun fetchData(username: String): FoloWidgetInfo {
//        val client = HttpClient(CIO)
//        val response = client.get("https://ktor.io/")
//        return response.body<FoloWidgetInfo.Available>()

        return FoloWidgetInfo.Available(
                FoloProfile(
                        FoloPlatform.Twitter,
                        "lamo",
                        "Golu",
                        566
                )
        )
    }
}