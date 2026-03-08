package com.amansingh.goldhouse.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: UserData?
)

@Serializable
data class UserData(
    val user: User,
    val token: String
)

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String
)
