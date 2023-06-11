package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.folo.components.PlatformSelection

@Composable
fun NewProfileScreen() {
    Scaffold(
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                Text(
                        text = "Track New Profile",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }
    ) { _ ->
        Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            var platform by remember { mutableStateOf("") }
            var username by remember { mutableStateOf("") }

            PlatformSelection(
                    options = listOf("Twitter", "YouTube"),
                    onSelect = { selection -> platform = selection }
            )

            OutlinedTextField(placeholder = { Text("username") },
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