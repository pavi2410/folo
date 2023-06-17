package me.pavi2410.folo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            var platform by remember { mutableStateOf(FoloPlatform.Twitter) }
            var username by remember { mutableStateOf("") }

            PlatformSelection { selection -> platform = selection }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Username") },
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