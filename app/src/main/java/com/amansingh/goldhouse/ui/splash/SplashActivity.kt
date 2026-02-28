package com.amansingh.goldhouse.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.amansingh.goldhouse.MainActivity
import com.amansingh.goldhouse.ui.Login.LoginActivity
import com.amansingh.goldhouse.ui.theme.GoldHouseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.isTokenValid.collectLatest { isTokenValid ->
                if (isTokenValid != null) {
                    val intent = if (isTokenValid) {
                        Intent(this@SplashActivity, MainActivity::class.java)
                    } else {
                        Intent(this@SplashActivity, LoginActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
