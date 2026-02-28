package com.amansingh.goldhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amansingh.goldhouse.ui.main.MainScreen
import com.amansingh.goldhouse.ui.theme.GoldHouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoldHouseTheme {
                MainScreen()
            }
        }
    }
}