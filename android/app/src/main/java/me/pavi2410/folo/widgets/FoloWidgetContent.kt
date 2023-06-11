package me.pavi2410.folo

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

@Composable
fun FoloWidgetContent() {
    TwitterCardWidget(data = testData.first())
}

@Preview(widthDp = 180, heightDp = 100)
@Composable
fun FoloWidgetPreview() {
    FoloWidgetContent()
}

@Composable
fun TwitterCardWidget(data: Map<String, String>) {
    val colors = listOf(Color(0xff3B82F6), Color(0xff2563EB))
    FoloCardWidget(colors) {
        Column {
            Text(
                data["display_name"] ?: "display_name",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color.White)
                )
            )
            Text(
                data["username"] ?: "username",
                style = TextStyle(color = ColorProvider(Color.White))
            )
        }
        Row {
            Text(
                data["followers"] ?: "followers",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color.White)
                )
            )
            Spacer(GlanceModifier.padding(2.dp))
            Text(
                "followers",
                style = TextStyle(color = ColorProvider(Color.White)),
                maxLines = 1,
            )
        }
    }
}

@Composable
fun YoutubeCardWidget(data: Map<String, String>) {
    val colors = listOf(Color(0xffEF4444), Color(0xffDC2626))
    FoloCardWidget(colors) {
        Row {
            Column {
                Text(
                    data["display_name"] ?: "display_name",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorProvider(Color.White)
                    )
                )
                Text(
                    data["username"] ?: "username",
                    style = TextStyle(color = ColorProvider(Color.White))
                )
            }
        }
        Row {
            Text(
                data["followers"] ?: "followers",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color.White)
                )
            )
            Spacer(GlanceModifier.padding(2.dp))
            Text(
                "subscribers",
                style = TextStyle(color = ColorProvider(Color.White)),
                maxLines = 1,
            )
        }
    }
}

@Composable
fun FoloCardWidget(colors: List<Color>, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(colors.last()),
        content = content
    )
}
