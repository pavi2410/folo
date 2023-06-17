package me.pavi2410.folo.widgets

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import kotlinx.coroutines.launch
import me.pavi2410.folo.FoloRepo
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.models.FoloPlatform
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
                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    topBar = {
                        TopAppBar {
                            Text("Configure Widget")
                        }
                    }) { paddingValues ->
                    Column(Modifier.padding(paddingValues)) {
                        val context = LocalContext.current
                        val coroutineScope = rememberCoroutineScope()
                        var platform by remember { mutableStateOf(FoloPlatform.Twitter) }
                        var username by remember { mutableStateOf("") }

                        PlatformSelection { selection -> platform = selection }

                        OutlinedTextField(placeholder = {
                            Text("Username")
                        }, value = username, onValueChange = { username = it })

                        Button(onClick = {
                            val glanceAppWidgetManager = GlanceAppWidgetManager(context)
                            val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)

                            coroutineScope.launch {
                                updateAppWidgetState(
                                    context = context,
                                    glanceId = glanceId,
                                    definition = FoloWidgetStateDef
                                ) {
                                    FoloRepo.fetchData(username)
                                }

                                FoloWidget().update(context, glanceId)

                                val resultValue =
                                    Intent().putExtra(
                                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                                        appWidgetId
                                    )
                                setResult(RESULT_OK, resultValue)
                                finish()
                            }
                        }) {
                            Text("Add Widget")
                        }
                    }
                }
            }
        }
    }
}
