package com.amansingh.goldhouse.navigation

sealed class Destination(val route: String) {
    object Splash : Destination("splash")
    object Login : Destination("login")
    object Home : Destination("home/{username}") {
        fun createRoute(username: String) = "home/$username"
    }
}
