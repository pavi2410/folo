package me.pavi2410.folo.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Column
import androidx.glance.layout.ColumnScope
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import me.pavi2410.folo.models.FoloProfile

class FoloWidget : GlanceAppWidget() {

    // Override the state definition to use our custom one using Kotlin serialization
    override val stateDefinition = FoloWidgetStateDef

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val foloWidgetInfo = currentState<FoloWidgetInfo>()

            GlanceTheme {
                when (foloWidgetInfo) {
                    is FoloWidgetInfo.Available -> {
                        FoloCardWidget(foloWidgetInfo.profile)
                    }

                    FoloWidgetInfo.Loading -> {
                        Column(GlanceModifier.appWidgetBackground()) {
                            CircularProgressIndicator()
                        }
                    }

                    is FoloWidgetInfo.Unavailable -> TODO()
                }
            }
        }
    }
}

@Composable
fun FoloCardWidget(data: FoloProfile) {
    val colors = listOf(Color(0xff3B82F6), Color(0xff2563EB))
    Column(
            modifier = GlanceModifier
                    .appWidgetBackground()
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(colors.last())
    ) {
        Column {
            Text(
                    data.displayName,
                    style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ColorProvider(Color.White)
                    )
            )
            Text(
                    data.username,
                    style = TextStyle(color = ColorProvider(Color.White))
            )
        }
        Row {
            Text(
                    data.followers.toString(),
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
