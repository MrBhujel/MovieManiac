package com.example.moviemaniac.feature.mainScreen.presentation


sealed class MainScreenUiEvent {
    data class NavItemChanged(val selectedRoute: String) : MainScreenUiEvent()
    data class BottomNavItemClicked(val isBottomNavItemClicked: Boolean) : MainScreenUiEvent()
}