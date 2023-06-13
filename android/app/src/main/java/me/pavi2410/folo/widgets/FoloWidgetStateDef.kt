package me.pavi2410.folo.widgets

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object FoloWidgetStateDef : GlanceStateDefinition<FoloWidgetInfo> {

    private const val DATA_STORE_FILENAME = "folo_widget_store"

    /**
     * Use the same file name regardless of the widget instance to share data between them
     *
     * If you need different state/data for each instance, create a store using the provided fileKey
     */
    private val Context.datastore by dataStore(DATA_STORE_FILENAME, FoloWidgetInfoSerializer)

    override suspend fun getDataStore(
        context: Context,
        fileKey: String
    ): DataStore<FoloWidgetInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }
}