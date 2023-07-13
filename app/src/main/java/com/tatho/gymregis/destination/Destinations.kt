package com.tatho.gymregis.destination

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatho.gymregis.presentations.components.SingUpScreen
import com.tatho.presentation.BodyMeasurementScreen
import com.tatho.presentation.BodyMeasurementViewModel
import com.tatho.presentation.MenuScreen
import com.tatho.presentation.MenuViewModel

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object BodyMeasurement : Destinations("register")
    object Main : Destinations("main")
    object Wifi : Destinations("wifi")
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Login.route
    ) {
        composable(Destinations.Login.route) {
            SingUpScreen { navController.navigate(Destinations.Main.route) }
        }
        composable(Destinations.Main.route) {
            val viewModel: MenuViewModel = hiltViewModel<MenuViewModel>()
            val context = LocalContext.current
            MenuScreen(
                { route ->
                    resolveNavigation(route, navController, context)
                },
                viewModel = viewModel,
            )
        }
        composable(Destinations.BodyMeasurement.route) {
            val viewModel: BodyMeasurementViewModel = hiltViewModel<BodyMeasurementViewModel>()
            BodyMeasurementScreen(
                { }, viewModel = viewModel,
            )
        }
    }
}

fun resolveNavigation(it: String, navController: NavHostController, context: Context) {
    when (it) {
        Destinations.Wifi.route -> context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        Destinations.Login.route -> navController.navigate(Destinations.Main.route)
        Destinations.Main.route -> navController.navigate(Destinations.Main.route)
        Destinations.BodyMeasurement.route -> navController.navigate(Destinations.BodyMeasurement.route)
    }
}



