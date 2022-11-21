package com.github.zhirnoov.julia.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = deepPurple200,
    onPrimary = black,
    primaryVariant = deepPurple700,
    secondary = green200,
    onBackground = white,
    error = deepPurple500,
    onSurface = black,
    onError = white,
    background = black,
)

private val LightColorPalette = lightColors(
    primary = deepPurple500,
    primaryVariant = deepPurple700,
    secondary = green200,
    onBackground = black,
    background = white,
    error = deepPurple500,
    onSurface = black,
    onError = white,
    onPrimary = white
)

@Composable
fun JuliaAppTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}