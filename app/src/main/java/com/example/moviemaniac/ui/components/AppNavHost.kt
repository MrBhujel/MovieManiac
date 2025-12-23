package com.example.moviemaniac.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.feature.firstOpenScreen.presentation.components.FirstInitialScreenRoute
import com.example.moviemaniac.feature.mainScreen.presentation.components.MainScreenRoute
import com.example.moviemaniac.feature.movieDetailScreen.component.MovieDetailScreenRoute

@Composable
fun AppNavHost(navController: NavHostController) {
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.mainScreen, builder = {

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
            MainScreenRoute(navController = navController)
        }

        // Navigating to home screen
        composable(
            route = NavRoutes.mainScreen,
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
            MainScreenRoute(navController = navController)
        }

        // Navigating to movie detail screen
        composable(
            route = "${NavRoutes.movieDetailScreen}/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            ),
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
            val movieId = it.arguments?.getInt("movieId")!!
            MovieDetailScreenRoute(movieId = movieId)
        }
    })
}