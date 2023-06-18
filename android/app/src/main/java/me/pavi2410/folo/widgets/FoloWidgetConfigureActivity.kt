package me.pavi2410.folo.widgets

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.pavi2410.folo.screens.WidgetConfigureScreen
import me.pavi2410.folo.ui.theme.FoloTheme

/**
 * The configuration screen for the [FoloWidget] AppWidget.
 */
class FoloWidgetConfigureActivity : ComponentActivity() {

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_CANCELED, resultValue)

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        setContent {
            FoloTheme {
                WidgetConfigureScreen(
                    appWidgetId,
                    onConfigure = {
                        val resultValue = Intent()
                            .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                        setResult(RESULT_OK, resultValue)
                        finish()
                    }
                )
            }
        }
    }
}
