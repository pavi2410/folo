package me.pavi2410.folo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.ui.platformMetrics

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlatformMetricSelection(platform: FoloPlatform, onSelect: (String) -> Unit) {
    val options = remember(platform) { platformMetrics(platform) }
    var selectedOption by remember(platform) { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Metric") },
            readOnly = true,
            value = selectedOption,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
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
