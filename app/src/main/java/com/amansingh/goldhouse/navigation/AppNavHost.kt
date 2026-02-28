package com.amansingh.goldhouse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amansingh.goldhouse.navigation.navGraph.homeNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Login.route,
        modifier = modifier
    ) {
        homeNavGraph(navController)
    }
}