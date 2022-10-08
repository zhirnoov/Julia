package com.github.zhirnoov.julia.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = deepPurple200,
    primaryVariant = deepPurple700,
    secondary = green200
)

private val LightColorPalette = lightColors(
    primary = deepPurple500,
    primaryVariant = deepPurple700,
    secondary = green200
)

@Composable
fun JuliaAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}