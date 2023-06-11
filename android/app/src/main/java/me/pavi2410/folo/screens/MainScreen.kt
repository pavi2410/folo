package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import me.pavi2410.folo.components.TwitterCard
import me.pavi2410.folo.components.YoutubeCard
import me.pavi2410.folo.testData
import me.pavi2410.folo.ui.theme.TailwindColors

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Row {
                Text(
                    text = "Folo",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                Text(
                    "by pavi2410 :P",
                    color = TailwindColors.gray500,
                    modifier = Modifier.alignByBaseline()
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Track New Profile") },
                onClick = {
                    navController.navigate("new_profile")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(testData + testData + testData) { data ->
                when (data["platform"]) {
                    "twitter" -> {
                        TwitterCard(data)
                    }
                    "youtube" -> {
                        YoutubeCard(data)
                    }
                }
            }
        }
    }
}