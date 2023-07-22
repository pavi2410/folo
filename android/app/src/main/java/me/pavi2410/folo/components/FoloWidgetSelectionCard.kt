package me.pavi2410.folo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.folo.compactDecimalFormat
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.ui.backgroundGradient
import me.pavi2410.folo.ui.followersText
import me.pavi2410.folo.ui.platformIcon

@Composable
fun FoloWidgetSelectionCard(
    data: FoloProfile,
    selected: Boolean,
    onSelect: () -> Unit
) {
    val platformIcon = remember { platformIcon(data.platform) }
    val colors = remember { backgroundGradient(data.platform) }
    val followersText = remember { followersText(data.platform) }
    val followersCount = remember { compactDecimalFormat(data.followers) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .then(
                if (selected) {
                    Modifier
                        .border(
                            1.dp,
                            colors.first(),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(8.dp)
                } else Modifier
            )
            .shadow(
                elevation = 16.dp,
                ambientColor = colors.last(),
                spotColor = colors.last()
            )
            .clickable {
                onSelect()
            }
            .background(
                Brush.verticalGradient(colors),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = platformIcon),
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(Modifier.padding(4.dp))
            Text(
                data.username,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
        }
        Row {
            Text(
                followersCount,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(Modifier.padding(2.dp))
            Text(
                followersText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}
