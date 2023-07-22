package me.pavi2410.folo.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import compose.icons.TablerIcons
import compose.icons.tablericons.InfoCircle
import compose.icons.tablericons.Settings
import me.pavi2410.folo.components.FoloCard
import me.pavi2410.folo.data.FoloRepo
import me.pavi2410.folo.models.FoloProfile
import me.pavi2410.folo.viewmodels.MainScreenViewModel
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    navController: NavController,
    foloRepo: FoloRepo = koinInject(),
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {

    val profiles = remember { mutableStateListOf<FoloProfile>() }

    LaunchedEffect(Unit) {
        profiles += mainScreenViewModel.getProfileRefs()
            .map {
                mainScreenViewModel.getProfile(it)
            }
    }

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
        LazyColumn(contentPadding = innerPadding) {
            items(profiles.toList()) { data ->
                FoloCard(
                    data,
                    onDetail = {
                        navController.navigate("stats/${data.id}")
                    },
                    onDelete = {
                        foloRepo.deleteProfile(data.id)
                    }
                )
            }
        }
    }
}