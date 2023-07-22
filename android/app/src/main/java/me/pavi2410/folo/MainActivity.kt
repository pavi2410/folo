package me.pavi2410.folo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.pavi2410.folo.screens.AboutScreen
import me.pavi2410.folo.screens.StatsScreen
import me.pavi2410.folo.screens.MainScreen
import me.pavi2410.folo.screens.NewProfileScreen
import me.pavi2410.folo.screens.SettingsScreen
import me.pavi2410.folo.ui.theme.FoloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FoloTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController)
                    }
                    composable("settings") {
                        SettingsScreen()
                    }
                    composable("about") {
                        AboutScreen()
                    }
                    composable("stats/{profile_id}") {
                        val profileId = it.arguments?.getString("profile_id")
                        StatsScreen(navController, profileId)
                    }
                    composable("new_profile") {
                        NewProfileScreen(navController)
                    }
                }
            }
        }
    }
}