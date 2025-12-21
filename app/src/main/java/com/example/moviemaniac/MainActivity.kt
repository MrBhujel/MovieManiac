package com.example.moviemaniac

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.moviemaniac.ui.components.AppNavHost
import com.example.moviemaniac.ui.theme.MovieManiacTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieManiacTheme {
                val navController = rememberNavController()
                AppNavHost(navController)

                Log.d("AuthInterceptor", "Token: '${BuildConfig.TMDB_READ_TOKEN}'")

            }
        }
    }
}