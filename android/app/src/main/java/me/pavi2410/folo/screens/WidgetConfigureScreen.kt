package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import kotlinx.coroutines.launch
import me.pavi2410.folo.FoloRepo
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidget
import me.pavi2410.folo.widgets.FoloWidgetInfo
import me.pavi2410.folo.widgets.FoloWidgetStateDef
import org.koin.androidx.compose.get
import org.koin.compose.koinInject

@Composable
fun WidgetConfigureScreen(appWidgetId: Int, onConfigure: () -> Unit, foloRepo: FoloRepo = koinInject()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var platform by remember { mutableStateOf(FoloPlatform.Twitter) }
    var username by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopAppBar {
                Text("Configure Widget")
            }
        }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
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
                        foloRepo.fetchWidgetInfo()
                    }

                    FoloWidget().update(context, glanceId)

                    onConfigure()
                }
            }) {
                Text("Add Widget")
            }
        }
    }
}
