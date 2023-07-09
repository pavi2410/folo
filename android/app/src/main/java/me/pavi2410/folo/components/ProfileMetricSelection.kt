package me.pavi2410.folo.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.ui.platformMetrics

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlatformMetricSelection(platform: FoloPlatform, onSelect: (String) -> Unit) {
    val options = remember(platform) { platformMetrics(platform) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOption,
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selection ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = selection
                        expanded = false
                        onSelect(selection)
                    }
                ) {
                    Text(text = selection)
                }
            }
        }
    }
}
