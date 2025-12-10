package com.example.moviemaniac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviemaniac.feature.firstOpenScreen.presentation.components.FirstInitialScreenRoute
import com.example.moviemaniac.ui.components.ParticleBackground
import com.example.moviemaniac.ui.theme.MovieManiacTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieManiacTheme {
                ParticleBackground()
            }
        }
    }
}