package com.example.moviemaniac

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.moviemaniac.ui.theme.Typography

private val DeepBlack = Color(0xFF000000)
private val SurfaceDark = Color(0xFF121212)
private val MovieRed = Color(0xFFE50914)
private val MovieGold = Color(0xFFFFD700)

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    val darkColorScheme = darkColorScheme(
        primary = MovieRed,
        secondary = MovieGold,
        tertiary = Color(0xFF03DAC5),
        background = DeepBlack,
        surface = SurfaceDark,
        onPrimary = Color.White,
        onSecondary = DeepBlack,
        onBackground = Color.White,
        onSurface = Color.White,
        surfaceVariant = Color(0xFF2C2C2C),
        onSurfaceVariant = Color.White.copy(alpha = 0.7f)
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set the status bar and navigation bar to be fully black
            window.statusBarColor = DeepBlack.toArgb()
            window.navigationBarColor = DeepBlack.toArgb()
            
            val insetsController = WindowCompat.getInsetsController(window, view)
            // false means light icons on dark background
            insetsController.isAppearanceLightStatusBars = false
            insetsController.isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography
    ) {
        // Wrap everything in a Surface with the background color to prevent white bars
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}