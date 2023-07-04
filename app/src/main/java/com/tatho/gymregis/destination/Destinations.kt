package com.tatho.gymregis.destination

//import com.tatho.menu_data.mapper.toDto
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatho.gymregis.presentations.components.SingUpScreen
import com.tatho.menu_data.mapper.toDto
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
fun NavigationHost(viewModel: MenuViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Login.route
    ) {
        composable(Destinations.Login.route) {
            SingUpScreen { navController.navigate(Destinations.Main.route) }
        }
        composable(Destinations.Main.route) {

            MenuScreen(
                { Destinations.BodyMeasurement.route },
                 viewModel.menuItems.collectAsState().value.data?.map {
                    it.toDto()
                } ?: emptyList()
            )

        }
        composable(Destinations.BodyMeasurement.route) {
            BodyMeasurementScreen { navController.navigate(Destinations.Main.route) }
        }
    }
}


