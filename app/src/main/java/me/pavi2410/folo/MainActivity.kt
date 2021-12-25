package me.pavi2410.folo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import me.pavi2410.folo.screens.DetailScreen
import me.pavi2410.folo.screens.MainScreen
import me.pavi2410.folo.screens.NewProfileScreen
import me.pavi2410.folo.ui.theme.FoloTheme


val testData = listOf(
    mapOf(
        "platform" to "twitter",
        "username" to "PavitraGolchha",
        "display_name" to "@pavi2410",
        "followers" to "296"
    ),
    mapOf(
        "platform" to "youtube",
        "username" to "PavitraGolchha",
        "display_name" to "Pavitra Golchha",
        "followers" to "1 000 000 000"
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FoloTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(navController)
                        }
                        composable("detail") {
                            DetailScreen()
                        }
                        composable("new_profile") {
                            NewProfileScreen()
                        }
                    }
                }
            }
        }
    }
}






