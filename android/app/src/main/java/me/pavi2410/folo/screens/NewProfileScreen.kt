package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import me.pavi2410.folo.FoloRepo
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import org.koin.compose.koinInject

@Composable
fun NewProfileScreen(navController: NavController, foloRepo: FoloRepo = koinInject()) {
    var platform by remember { mutableStateOf(FoloPlatform.Twitter) }
    var username by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.systemBarsPadding()) {
                Text(
                    text = "Track New Profile",
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
                    Icon(FeatherIcons.Check, contentDescription = null)
                },
                text = { Text("Done") },
                onClick = {
                    // todo:
                    //  - make sure username is not empty
                    //  - check if profile already exists
                    //  - check if username exists in the platform
                    //  - fetch followers count
                    foloRepo.addProfile(platform, username, 0)
                    navController.navigateUp()
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
        }
    }
}