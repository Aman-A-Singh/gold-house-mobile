package com.amansingh.goldhouse.repository

import com.amansingh.goldhouse.data.local.UserDao
import com.amansingh.goldhouse.data.local.UserEntity
import com.amansingh.goldhouse.data.model.User
import com.amansingh.goldhouse.data.remote.LoginRequest
import com.amansingh.goldhouse.network.ApiService
import com.amansingh.goldhouse.session.SessionManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val sessionManager: SessionManager
) {

    suspend fun login(username: String, password: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.success && response.data != null) {
                val user = response.data.user
                val token = response.data.token
                
                userDao.insertUser(UserEntity(user.id, user.firstName, user.lastName))
                sessionManager.saveAuthToken(token)
                sessionManager.saveUserId(user.id)

                Result.success(User(user.id, user.firstName, user.lastName, token))
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getLoggedInUser(): User? {
        val userId = sessionManager.getUserId()
        if (userId != -1) {
            return userDao.getUser(userId).first()?.let {
                User(it.id, it.firstName, it.lastName, sessionManager.getAuthToken() ?: "")
            }
        }
        return null
    }
}
