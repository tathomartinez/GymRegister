package com.tatho.gymregis.destination

//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatho.gymregis.presentations.components.SingUpScreen

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object BodyMeasurementForm : Destinations("register")
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
            SingUpScreen{navController.navigate(Destinations.Main.route)}
        }
        composable(Destinations.Main.route) {

        }
        composable(Destinations.BodyMeasurementForm.route) {

        }
    }
}


