package me.pavi2410.folo.widgets

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object FoloWidgetStateDef: GlanceStateDefinition<FoloWidgetInfo> {
    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<FoloWidgetInfo> {
        TODO("Not yet implemented")
    }

    override fun getLocation(context: Context, fileKey: String): File {
        TODO("Not yet implemented")
    }
}