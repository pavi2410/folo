package me.pavi2410.folo

import android.content.Context
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import me.pavi2410.folo.sqldelight.FoloProfile
import me.pavi2410.folo.widgets.FoloWidgetWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val appModule = module {
    single { createDatabase(androidContext()) }
    single { FoloRepo(get()) }
    workerOf(::FoloWidgetWorker)
}

private fun createDatabase(context: Context): FoloDatabase {
    val driver = AndroidSqliteDriver(FoloDatabase.Schema, context, "folo.db")
    return FoloDatabase(
        driver,
        FoloProfile.Adapter(
            platformAdapter = EnumColumnAdapter()
        )
    )
}