package com.tatho.gymregis.destination

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatho.gymregis.presentations.components.SingUpScreen
import com.tatho.menu_data.mapper.toDto
import androidx.hilt.navigation.compose.hiltViewModel
import com.tatho.presentation.BodyMeasurementScreen
import com.tatho.presentation.MenuScreen
import com.tatho.presentation.MenuViewModel

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object BodyMeasurement : Destinations("register")
    object Main : Destinations("main")
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

            MenuScreen(
                { Destinations.BodyMeasurement.route}, viewModel = viewModel ,
            )

        }
        composable(Destinations.BodyMeasurement.route) {
            BodyMeasurementScreen { navController.navigate(Destinations.Main.route) }
        }
    }
}


