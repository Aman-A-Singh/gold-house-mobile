package com.amansingh.goldhouse.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val GoldGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFF2B70D), Color(0xFFB88614)),
    start = Offset(0f, 0f), // Corresponds to 135deg angle
    end = Offset.Infinite
)

object GoldHouseColors {
    val PrimaryForeground =Color(0xFFF0D175) // --primary-foreground
    val InputBackground = Color(0xFF333B4C)
    val InputBorder = Color(0xFFD1D8E0) // --input
}