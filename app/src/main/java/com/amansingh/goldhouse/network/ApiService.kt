package com.amansingh.goldhouse.network

import com.amansingh.goldhouse.data.remote.LoginRequest
import com.amansingh.goldhouse.data.remote.LoginResponse
import com.amansingh.goldhouse.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
