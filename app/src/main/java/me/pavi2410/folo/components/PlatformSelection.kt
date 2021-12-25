package me.pavi2410.folo.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun PlatformSelection(options: List<String>, onSelect: (String) -> Unit) {
    var selected by remember { mutableStateOf(options.first()) }

    val onSelectedChange = { text: String ->
        selected = text
        onSelect(text)
    }

    Column(Modifier.selectableGroup()) {
        Text("Select a platform", style = MaterialTheme.typography.h6)

        options.forEach { text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selected),
                        onClick = { onSelectedChange(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selected),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
