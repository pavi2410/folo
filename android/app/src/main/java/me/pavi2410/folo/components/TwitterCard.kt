package me.pavi2410.folo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Twitter
import me.pavi2410.folo.models.FoloProfile

@Composable
fun TwitterCard(data: FoloProfile) {
    val uriHandler = LocalUriHandler.current

    val colors = listOf(Color(0xff3B82F6), Color(0xff2563EB))
    FoloCard(colors, onViewClick = {
        uriHandler.openUri("https://twitter.com/${data.username}")
    }) {
        Row {
            Icon(
                    imageVector = FeatherIcons.Twitter,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(4.dp)
            )
            Spacer(Modifier.padding(2.dp))
            Column {
                Text(
                        data.displayName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                )
                Text(
                        data.username,
                        color = Color.White
                )
            }
        }
        Row {
            Text(
                    data.followers.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.alignByBaseline()
            )
            Spacer(Modifier.padding(2.dp))
            Text(
                    "followers",
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.alignByBaseline()
            )
        }
    }
}