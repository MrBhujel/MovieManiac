package com.example.moviemaniac.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.feature.firstOpenScreen.presentation.components.FirstInitialScreenRoute
import com.example.moviemaniac.feature.homeScreen.presentation.components.HomeScreenRoute

@Composable
fun AppNavHost(navController: NavHostController) {
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.screenA, builder = {

        // Initial first opening screen
        composable(
            route = NavRoutes.screenA,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            FirstInitialScreenRoute(navController = navController)
        }

        // Navigating to home screen
        composable(
            route = NavRoutes.screenB,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            HomeScreenRoute()
        }
    })
}