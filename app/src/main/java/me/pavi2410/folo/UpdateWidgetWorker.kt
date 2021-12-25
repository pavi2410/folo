package me.pavi2410.folo

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.pavi2410.folo.TwitterFollowWidget.Companion.notifyAppWidgetViewDataChanged
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

const val bearerToken =
    "AAAAAAAAAAAAAAAAAAAAAGjHWgEAAAAAk%2Fi0c3QnM00gyn18ZdT3Jm7GH9Q%3DuqEus7AOdJxBEPMFHCaZ3rUoNo2lps5b2gKjXXnsdacH7kNr6E"

class UpdateWidgetWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val widgetId = inputData.getInt("widgetId", -1)
        val context = applicationContext

        withContext(Dispatchers.IO) {
            val username = loadTitlePref(context, widgetId).split(":")[1]
            val followers = fetchData(username)
            saveFollowersPref(context, username, followers)
            notifyAppWidgetViewDataChanged(context)
        }
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    private fun fetchData(username: String): Int {
        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("https://api.twitter.com/2/users/by/username/$username?user.fields=public_metrics")
            .get()
            .addHeader("Authorization", "Bearer $bearerToken")
            .build()

        val response: Response = client.newCall(request).execute()

        println("recv: $response")

        val body = response.body?.string() ?: return -1

        println("recv: $username $body")

        return JSONObject(body)
            .getJSONObject("data")
            .getJSONObject("public_metrics")
            .getInt("followers_count")
    }

    companion object {
        //Define a work name to uniquely identify this worker.
        const val WORK_NAME = "me.pavi2410.folo.UpdateWidgetWorker"
    }
}

