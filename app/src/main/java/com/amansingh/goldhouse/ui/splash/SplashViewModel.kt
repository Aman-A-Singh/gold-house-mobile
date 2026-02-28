package com.amansingh.goldhouse.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amansingh.goldhouse.session.SessionManager
import com.amansingh.goldhouse.util.JwtUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _isTokenValid = MutableStateFlow<Boolean?>(null)
    val isTokenValid = _isTokenValid.asStateFlow()

    init {
        viewModelScope.launch {
            val token = sessionManager.getAuthToken()
            _isTokenValid.value = token != null && !JwtUtils.isTokenExpired(token)
        }
    }
}
