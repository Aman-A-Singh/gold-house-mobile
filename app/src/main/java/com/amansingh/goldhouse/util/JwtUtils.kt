package com.amansingh.goldhouse.util

import android.util.Base64
import org.json.JSONObject

object JwtUtils {
    fun isTokenExpired(token: String): Boolean {
        return try {
            val split = token.split(".")
            val decodedBytes = Base64.decode(split[1], Base64.URL_SAFE)
            val decodedString = String(decodedBytes, Charsets.UTF_8)
            val json = JSONObject(decodedString)
            val exp = json.getLong("exp")
            val currentTime = System.currentTimeMillis() / 1000
            exp < currentTime
        } catch (e: Exception) {
            true // Assume expired if any error occurs
        }
    }
}
