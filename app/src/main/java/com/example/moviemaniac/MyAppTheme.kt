package com.example.moviemaniac

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.moviemaniac.ui.theme.Typography

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    val darkColorScheme = darkColorScheme(
        primary = Color(0xFFBB86FC),
        secondary = Color(0xFF03DAC5),
        background = Color.Black,
        surface = Color.Black,
        onPrimary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
    )

    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography,
        content = content
    )
}