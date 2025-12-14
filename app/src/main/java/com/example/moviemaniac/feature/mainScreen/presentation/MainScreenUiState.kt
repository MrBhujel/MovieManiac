package com.example.moviemaniac.feature.mainScreen.presentation

import com.example.moviemaniac.core.util.NavRoutes


data class MainScreenUiState(
    val selectedRoute: String = NavRoutes.movieScreen,
    val isNavItemClicked: Boolean = false
)