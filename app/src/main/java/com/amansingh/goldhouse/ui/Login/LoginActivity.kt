package com.amansingh.goldhouse.ui.Login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.amansingh.goldhouse.navigation.AppNavHost
import com.amansingh.goldhouse.ui.theme.GoldHouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoldHouseTheme {
                AppNavHost(
                    navController = rememberNavController()
                )
            }
        }
    }
}