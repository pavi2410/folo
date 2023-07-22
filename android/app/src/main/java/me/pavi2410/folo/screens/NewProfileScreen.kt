package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compose.icons.TablerIcons
import compose.icons.tablericons.Check
import kotlinx.coroutines.launch
import me.pavi2410.folo.data.FoloRepo
import me.pavi2410.folo.components.PlatformMetricSelection
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.models.FoloPlatform
import org.koin.compose.koinInject

@Composable
fun NewProfileScreen(
    navController: NavController,
    foloRepo: FoloRepo = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()
    var platform by remember { mutableStateOf(FoloPlatform.twitter) }
    var username by remember { mutableStateOf("") }
    var platformMetric by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.systemBarsPadding()) {
                Text(
                    text = "track a new profile",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.navigationBarsPadding(),
                icon = {
                    Icon(TablerIcons.Check, contentDescription = null)
                },
                text = { Text("Done") },
                onClick = {
                    coroutineScope.launch {
                        foloRepo.addProfile(platform, username, platformMetric)
                        navController.navigateUp()
                    }
                }
            )
        }
    ) { _ ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            PlatformSelection { selection -> platform = selection }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Username") },
                value = username,
                onValueChange = { username = it }
            )

            PlatformMetricSelection(platform) { selection -> platformMetric = selection }
        }
    }
}