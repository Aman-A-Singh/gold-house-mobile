package com.amansingh.goldhouse.navigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.amansingh.goldhouse.ui.Login.ui.LoginScreen
import com.amansingh.goldhouse.navigation.Destination
import com.amansingh.goldhouse.ui.home.HomeScreen
import com.amansingh.goldhouse.utils.Constants

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    composable(Destination.Login.route) {
        LoginScreen(navController)
    }
    composable(
        route = Destination.Home.route,
        arguments = listOf(navArgument(Constants.USERNAME) { defaultValue = "" })
    ) { backStackEntry ->
        val username = backStackEntry.arguments?.getString(Constants.USERNAME) ?: ""
        HomeScreen(username)
    }
}
