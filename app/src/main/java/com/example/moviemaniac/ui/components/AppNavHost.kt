package com.example.moviemaniac.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.feature.firstOpenScreen.presentation.components.FirstInitialScreenRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.screenA, builder = {
        composable(NavRoutes.screenA) {
            FirstInitialScreenRoute()
        }
    })
}