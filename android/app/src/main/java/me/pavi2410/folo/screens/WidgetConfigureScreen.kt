package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import kotlinx.coroutines.launch
import me.pavi2410.folo.FoloRepo
import me.pavi2410.folo.components.FoloWidgetSelectionCard
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.widgets.FoloWidget
import me.pavi2410.folo.widgets.FoloWidgetStateDef
import org.koin.compose.koinInject

@Composable
fun WidgetConfigureScreen(
    appWidgetId: Int,
    onConfigure: () -> Unit,
    foloRepo: FoloRepo = koinInject()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val profiles by remember { foloRepo.getAllProfiles() }.collectAsState(initial = emptyList())
    var selected by remember { mutableStateOf<FoloProfile?>(null) }

    suspend fun updateWidget(profile: FoloProfile) {
        foloRepo.upsertWidget(appWidgetId, profile.id)

        val glanceAppWidgetManager = GlanceAppWidgetManager(context)
        val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)

        updateAppWidgetState(
            context = context,
            glanceId = glanceId,
            definition = FoloWidgetStateDef
        ) {
            foloRepo.fetchWidgetInfo(appWidgetId)
        }

        FoloWidget().update(context, glanceId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.systemBarsPadding()) {
                Text(
                    text = "Select a profile to display as widget",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (selected != null) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.navigationBarsPadding(),
                    text = { Text("Track New Profile") },
                    onClick = {
                        coroutineScope.launch {
                            updateWidget(selected!!)
                            onConfigure()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(profiles) {
                FoloWidgetSelectionCard(
                    data = it,
                    selected = it == selected,
                    onSelect = { selected = it }
                )
            }
        }
    }
}
