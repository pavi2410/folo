package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import me.pavi2410.folo.components.TwitterCard
import me.pavi2410.folo.components.YoutubeCard
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile

val testData = listOf(
        FoloProfile(
                platform = FoloPlatform.Twitter,
                username = "PavitraGolchha",
                displayName = "@pavi2410",
                followers = 296
        ),
        FoloProfile(
                platform = FoloPlatform.Youtube,
                username = "PavitraGolchha",
                displayName = "Pavitra Golchha",
                followers = 1_000_000_000
        )
)

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
            modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
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
                when (data.platform) {
                    FoloPlatform.Twitter -> {
                        TwitterCard(data)
                    }

                    FoloPlatform.Youtube -> {
                        YoutubeCard(data)
                    }
                }
            }
        }
    }
}