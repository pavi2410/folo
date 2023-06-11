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
import compose.icons.feathericons.Youtube

@Composable
fun YoutubeCard(data: Map<String, String>) {
    val uriHandler = LocalUriHandler.current

    val colors = listOf(Color(0xffEF4444), Color(0xffDC2626))
    FoloCard(colors, onViewClick = {
        uriHandler.openUri("https://youtube.com/c/${data["username"]}")
    }) {
        Row {
            Icon(
                imageVector = FeatherIcons.Youtube,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(Modifier.padding(2.dp))
            Column {
                Text(
                    data["display_name"] ?: "display_name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    data["username"] ?: "username",
                    color = Color.White
                )
            }
        }
        Row {
            Text(
                data["followers"] ?: "followers",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alignByBaseline()
            )
            Spacer(Modifier.padding(2.dp))
            Text(
                "subscribers",
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}