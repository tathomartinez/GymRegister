package com.tatho.gymregis.destination

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object SingUpScreen : Destinations("singup")
    object BodyMeasurement : Destinations("register")
    object Main : Destinations("main")
    object Wifi : Destinations("wifi")
    object ShowSize : Destinations("showsize")
    object Routine : Destinations("routine")
}




