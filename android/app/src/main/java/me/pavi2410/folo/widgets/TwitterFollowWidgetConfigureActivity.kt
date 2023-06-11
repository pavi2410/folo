package me.pavi2410.folo

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.insets.ProvideWindowInsets
import me.pavi2410.folo.TwitterFollowWidget.Companion.updateAppWidget
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.ui.theme.FoloTheme

/**
 * The configuration screen for the [TwitterFollowWidget] AppWidget.
 */
class TwitterFollowWidgetConfigureActivity : ComponentActivity() {
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        setContent {
            FoloTheme {
                ProvideWindowInsets {
                    Column {
                        val context = LocalContext.current

                        var platform by remember { mutableStateOf("") }
                        var username by remember { mutableStateOf("") }

                        Text("Configure Widget")

                        PlatformSelection(listOf("twitter", "instagram", "reddit")) { selection -> platform = selection }

                        OutlinedTextField(placeholder = {
                            Text("username")
                        }, value = username, onValueChange = { username = it })

                        Button(onClick = {
                            // When the button is clicked, store the string locally
                            val widgetText = "$platform:$username"
                            saveTitlePref(context, appWidgetId, widgetText)

                            // It is the responsibility of the configuration activity to update the app widget
                            val appWidgetManager = AppWidgetManager.getInstance(context)
                            updateAppWidget(context, appWidgetManager, appWidgetId)

                            // Make sure we pass back the original appWidgetId
                            val resultValue = Intent()
                            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                            setResult(RESULT_OK, resultValue)
                            finish()
                        }) {
                            Text("Add Widget")
                        }
                    }
                }
            }
        }
    }
}
