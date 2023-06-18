package me.pavi2410.folo.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.ui.platformIcon

@Composable
fun PlatformSelection(onSelect: (FoloPlatform) -> Unit) {
    val options = remember { FoloPlatform.values() }
    var selectedOption by remember { mutableStateOf(options[0]) }
    val platformIcon = remember { FoloPlatform.values().associateWith { platformIcon(it) } }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Select a platform")

        options.forEach {
            Row(
                Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        if (selectedOption == it) Color.Blue else Color.Gray,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        selectedOption = it
                        onSelect(it)
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = platformIcon[it]!!,
                    contentDescription = null,
                )
                Spacer(Modifier.padding(8.dp))
                Text(
                    it.name,
                )
            }
        }
    }
}
