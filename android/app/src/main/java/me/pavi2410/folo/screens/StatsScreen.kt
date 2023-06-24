package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.config.CurveLineConfig
import com.himanshoe.charty.line.model.LineData
import me.pavi2410.folo.ui.theme.TailwindColors

@Composable
fun StatsScreen(
    navController: NavController,
    id: Int?
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.statusBarsPadding()) {
                Text(
                    text = "Stats",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        if (id == null) {
            Text("You shouldn't be here...")
        } else {
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier.padding(32.dp)
            ) {
                item {
                    CurveLineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 300.dp),
                        lineColor = Color.Transparent,
                        chartColors = listOf(
                            Color(TailwindColors.blue500),
                            Color(TailwindColors.blue600)
                        ),
                        lineData = listOf(
                            LineData(10F, 35F),
                            LineData(20F, 25F),
                            LineData(10F, 50F),
                            LineData(80F, 10F),
                            LineData(10F, 15F),
                            LineData(50F, 100F),
                            LineData(20F, 25F),
                        ),
                        curveLineConfig = CurveLineConfig(false)
                    )
                }
            }
        }
    }
}