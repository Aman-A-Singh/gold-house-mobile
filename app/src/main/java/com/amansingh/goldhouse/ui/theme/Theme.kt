package com.amansingh.goldhouse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF141C2E),
    onPrimary = Color(0xFFF2E274),
    secondary = Color(0xFFF2B70D),
    background = Color(0xFFF2F5F8),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFD11212)
)

@Composable
fun GoldHouseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}