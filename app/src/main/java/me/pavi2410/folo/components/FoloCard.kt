package me.pavi2410.folo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Eye
import compose.icons.feathericons.PieChart
import compose.icons.feathericons.Settings
import compose.icons.feathericons.Trash
import me.pavi2410.folo.coloredShadow

@Composable
fun FoloCard(
    colors: List<Color>,
    onViewClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .coloredShadow(colors.last())
            .background(
                Brush.verticalGradient(colors),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row {
            Column(Modifier.fillMaxWidth(.75f)) {
                content()
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxSize()
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = FeatherIcons.Eye,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = FeatherIcons.PieChart,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxSize()
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = FeatherIcons.Settings,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = FeatherIcons.Trash,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}