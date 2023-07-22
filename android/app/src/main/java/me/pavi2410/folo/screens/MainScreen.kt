package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compose.icons.TablerIcons
import compose.icons.tablericons.InfoCircle
import compose.icons.tablericons.MoodCrazyHappy
import compose.icons.tablericons.Settings
import me.pavi2410.folo.components.FoloCard
import me.pavi2410.folo.viewmodels.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {

    val isLoading by mainScreenViewModel.isLoading.collectAsState()
    val profiles by mainScreenViewModel.profiles.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {
                    Text(
                        text = "folo",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("settings")
                    }) {
                        Icon(
                            imageVector = TablerIcons.Settings,
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("about")
                    }) {
                        Icon(
                            imageVector = TablerIcons.InfoCircle,
                            contentDescription = "About"
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.navigationBarsPadding(),
                text = { Text("Track New Profile") },
                onClick = {
                    navController.navigate("new_profile")
                }
            )
        }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (profiles.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = TablerIcons.MoodCrazyHappy,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(128.dp)
                    )

                    Text("No profiles added yet!")
                }

            } else {
                LazyColumn(contentPadding = innerPadding) {
                    items(profiles) { data ->
                        FoloCard(
                            data,
                            onDetail = {
                                navController.navigate("stats/${data.id}")
                            },
                            onDelete = {
                                mainScreenViewModel.deleteProfile(data.id)
                            }
                        )
                    }
                }
            }
        }
    }
}