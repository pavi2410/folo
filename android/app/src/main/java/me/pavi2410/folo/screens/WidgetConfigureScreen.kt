package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import compose.icons.TablerIcons
import compose.icons.tablericons.MoodCrazyHappy
import kotlinx.coroutines.launch
import me.pavi2410.folo.data.FoloRepo
import me.pavi2410.folo.components.FoloWidgetSelectionCard
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.viewmodels.MainScreenViewModel
import me.pavi2410.folo.widgets.FoloWidget
import me.pavi2410.folo.widgets.FoloWidgetInfo
import me.pavi2410.folo.widgets.FoloWidgetStateDef
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun WidgetConfigureScreen(
    appWidgetId: Int,
    onConfigure: () -> Unit,
    foloRepo: FoloRepo = koinInject(),
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val isLoading by mainScreenViewModel.isLoading.collectAsState()
    val profiles by mainScreenViewModel.profiles.collectAsState()

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
            FoloWidgetInfo.Available(
                FoloProfile(
                    "999",
                    profile.platform,
                    profile.username,
                    profile.followers
                )
            )
        }

        FoloWidget().update(context, glanceId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.systemBarsPadding()) {
                Text(
                    text = "select a profile to display as widget",
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
                    text = { Text("Add Widget") },
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
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (profiles.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = TablerIcons.MoodCrazyHappy,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(128.dp)
                    )

                    Text("No profiles added yet!")
                }
            } else {
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
    }
}
