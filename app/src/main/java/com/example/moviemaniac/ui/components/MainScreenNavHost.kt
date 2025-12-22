package com.example.moviemaniac.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemaniac.feature.settingsScreen.presentation.SettingsScreen
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.feature.homeScreen.presentation.components.HomeScreenRoute
import com.example.moviemaniac.feature.recentScreen.presentation.RecentScreen
import com.example.moviemaniac.feature.searchScreen.presentation.components.SearchScreenRoute

@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    mainNavController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = NavRoutes.movieScreen, builder = {

        composable(
            route = NavRoutes.movieScreen,
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
            HomeScreenRoute(mainNavController = mainNavController)
        }
        composable(
            route = NavRoutes.tvShowScreen,
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
            RecentScreen()
        }
        composable(
            route = NavRoutes.searchScreen,
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
            SearchScreenRoute()
        }
        composable(
            route = NavRoutes.settingScreen,
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
            SettingsScreen()
        }
    })

}