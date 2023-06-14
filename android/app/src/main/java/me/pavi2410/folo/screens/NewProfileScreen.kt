package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.folo.components.PlatformSelection
import me.pavi2410.folo.models.FoloPlatform

@Composable
fun NewProfileScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            Text(
                text = "Track New Profile",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    ) { _ ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            var platform by remember { mutableStateOf(FoloPlatform.Twitter) }
            var username by remember { mutableStateOf("") }

            PlatformSelection(
                options = listOf("Twitter", "Youtube"),
                onSelect = { selection -> platform = FoloPlatform.valueOf(selection) }
            )

            OutlinedTextField(placeholder = { Text("Username") },
                value = username,
                onValueChange = { username = it }
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text("CANCEL")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text("DONE")
                }
            }
        }
    }
}