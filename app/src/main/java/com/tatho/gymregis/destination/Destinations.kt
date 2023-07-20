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
import com.tatho.login_presentation.LoginScreen
import com.tatho.login_presentation.LoginViewModel
import com.tatho.presentation.BodyMeasurementScreen
import com.tatho.presentation.BodyMeasurementViewModel
import com.tatho.presentation.MenuScreen
import com.tatho.presentation.MenuViewModel
import com.tatho.showsize_presentation.ShowSizeScreen
import com.tatho.showsize_presentation.ShowSizeViewModel
import com.tatho.sing_presentation.NewSignUpScreen
import com.tatho.sing_presentation.SignUpViewModel
import com.tatho.sing_presentation.SingUpScreen

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object SingUpScreen : Destinations("singup")
    object BodyMeasurement : Destinations("register")
    object Main : Destinations("main")
    object Wifi : Destinations("wifi")
    object ShowSizeScreen : Destinations("showsize")
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val sharedPreferences = LocalContext.current.getSharedPreferences("appPrefernece", Context.MODE_PRIVATE)
    val uid = sharedPreferences.getString("Uid", "")

    NavHost(
        navController = navController,
        startDestination = if (uid.isNullOrBlank()) Destinations.Login.route else Destinations.Main.route
    ) {
        //singup
        composable(Destinations.SingUpScreen.route) {
            val viewModel: SignUpViewModel = hiltViewModel()
            val context = LocalContext.current
            NewSignUpScreen(
                { route ->
                    resolveNavigation(route, navController, context)
                },
                viewModel = viewModel
            )
//            SingUpScreen(
//                { route ->
//                    resolveNavigation(route, navController, context)
//                },
//                viewModel = viewModel
//            )
        }
        //main
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
        //register
        composable(Destinations.BodyMeasurement.route) {
            val viewModel: BodyMeasurementViewModel = hiltViewModel()
            BodyMeasurementScreen(
                { }, viewModel = viewModel,
            )
        }
        //login
        composable(Destinations.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen({ route -> navController.navigate(route) }, viewModel = viewModel)
        }
        composable(Destinations.ShowSizeScreen.route) {
            val viewModel: ShowSizeViewModel = hiltViewModel()
            ShowSizeScreen({ route -> navController.navigate(route) }, viewModel = viewModel)
        }
    }
}

fun resolveNavigation(it: String, navController: NavHostController, context: Context) {
    when (it) {
        Destinations.Wifi.route -> context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        Destinations.Login.route -> navController.navigate(Destinations.Login.route)
        Destinations.Main.route -> navController.navigate(Destinations.Main.route)
        Destinations.BodyMeasurement.route -> navController.navigate(Destinations.BodyMeasurement.route)
        Destinations.ShowSizeScreen.route -> navController.navigate(Destinations.ShowSizeScreen.route)
    }
}



