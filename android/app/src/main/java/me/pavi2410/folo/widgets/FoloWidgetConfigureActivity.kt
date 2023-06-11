package me.pavi2410.folo.widgets

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.ui.theme.FoloTheme

/**
 * The configuration screen for the [FoloWidget] AppWidget.
 */
class FoloWidgetConfigureActivity : ComponentActivity() {
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
                Scaffold(
                        modifier = Modifier.systemBarsPadding(),
                        topBar = {
                            TopAppBar {
                                Text("Configure Widget")
                            }
                        }) { _ ->
                    Column {
                        val context = LocalContext.current

                        var platform by remember { mutableStateOf("") }
                        var username by remember { mutableStateOf("") }

                        PlatformSelection(listOf("twitter", "instagram", "reddit")) { selection -> platform = selection }

                        OutlinedTextField(placeholder = {
                            Text("Username")
                        }, value = username, onValueChange = { username = it })

                        Button(onClick = {
                            // Make sure we pass back the original appWidgetId
                            val resultValue = Intent()
                            resultValue.putExtra(
                                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                                    appWidgetId
                            )
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
