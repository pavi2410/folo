package me.pavi2410.folo.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import me.pavi2410.folo.data.FoloRepo
import kotlin.time.Duration.Companion.days
import kotlin.time.toJavaDuration

class FoloWidgetWorker(
    private val foloRepo: FoloRepo,
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(FoloWidget::class.java)
        return try {
            // Update state to indicate loading
            setWidgetState(glanceIds) {
                FoloWidgetInfo.Loading
            }
            // Update state with new data
            setWidgetState(glanceIds) { glanceId ->
                val appWidgetId = manager.getAppWidgetId(glanceId)
                foloRepo.fetchWidgetInfo(appWidgetId)
            }

            Result.success()
        } catch (e: Exception) {
            setWidgetState(glanceIds) {
                FoloWidgetInfo.Unavailable(e.message.orEmpty())
            }
            if (runAttemptCount < 10) {
                // Exponential backoff strategy will avoid the request to repeat
                // too fast in case of failures.
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    /**
     * Update the state of all widgets and then force update UI
     */
    private suspend fun setWidgetState(glanceIds: List<GlanceId>, newState: (GlanceId) -> FoloWidgetInfo) {
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                    context = context,
                    definition = FoloWidgetStateDef,
                    glanceId = glanceId,
                    updateState = { newState(glanceId) }
            )
        }
        FoloWidget().updateAll(context)
    }

    companion object {
        private val uniqueWorkName = FoloWidgetWorker::class.java.simpleName

        /**
         * Enqueues a new worker to refresh weather data only if not enqueued already
         *
         * Note: if you would like to have different workers per widget instance you could provide
         * the unique name based on some criteria (e.g selected weather location).
         *
         * @param force set to true to replace any ongoing work and expedite the request
         */
        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<FoloWidgetWorker>(
                    1.days.toJavaDuration()
            )
            var workPolicy = ExistingPeriodicWorkPolicy.KEEP

            // Replace any enqueued work and expedite the request
            if (force) {
                workPolicy = ExistingPeriodicWorkPolicy.UPDATE
            }

            manager.enqueueUniquePeriodicWork(
                    uniqueWorkName,
                    workPolicy,
                    requestBuilder.build()
            )
        }

        /**
         * Cancel any ongoing worker
         */
        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
        }
    }
}

