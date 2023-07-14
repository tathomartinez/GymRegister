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
import com.tatho.presentation.BodyMeasurementScreen
import com.tatho.presentation.BodyMeasurementViewModel
import com.tatho.presentation.MenuScreen
import com.tatho.presentation.MenuViewModel
import com.tatho.sing_presentation.SignUpViewModel
import com.tatho.sing_presentation.SingUpScreen

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
            val viewModel: SignUpViewModel = hiltViewModel()
            SingUpScreen(
                { navController.navigate(Destinations.Main.route) },
                viewModel = viewModel
            )
        }
        composable(Destinations.Main.route) {
            val viewModel: MenuViewModel = hiltViewModel()
            val context = LocalContext.current
            MenuScreen(
                { route ->
                    resolveNavigation(route, navController, context)
                },
                viewModel = viewModel,
            )
        }
        composable(Destinations.BodyMeasurement.route) {
            val viewModel: BodyMeasurementViewModel = hiltViewModel()
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



