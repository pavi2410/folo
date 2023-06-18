package me.pavi2410.folo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Eye
import compose.icons.feathericons.PieChart
import compose.icons.feathericons.Settings
import compose.icons.feathericons.Trash
import me.pavi2410.folo.compactDecimalFormat
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.ui.backgroundGradient
import me.pavi2410.folo.ui.followersText
import me.pavi2410.folo.ui.platformIcon
import me.pavi2410.folo.ui.profileLink

@Composable
fun FoloCard(data: FoloProfile, onDelete: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    val platformIcon = remember { platformIcon(data.platform) }
    val colors = remember { backgroundGradient(data) }
    val followersText = remember { followersText(data) }
    val followersCount = remember { compactDecimalFormat(data.followers) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 16.dp,
                ambientColor = colors.last(),
                spotColor = colors.last()
            )
            .background(
                Brush.verticalGradient(colors),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.75f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = platformIcon,
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
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(onClick = {
                    uriHandler.openUri(profileLink(data))
                }) {
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
                IconButton(onClick = onDelete) {
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
