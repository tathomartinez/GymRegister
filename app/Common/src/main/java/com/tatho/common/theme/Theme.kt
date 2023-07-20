package com.tatho.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPalette = lightColors(
    primary = Color.Red,
    primaryVariant = Color.Red,
    secondary = Color.Red,
    background = Color.Red,
    surface = Color.Red,
)

@Composable
fun GymTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
//        DarkColorPalette
        lightColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}