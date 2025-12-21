package com.example.moviemaniac.feature.mainScreen.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviemaniac.feature.mainScreen.presentation.MainScreen
import com.example.moviemaniac.feature.mainScreen.presentation.MainScreenViewModel

@Composable
fun MainScreenRoute(
    viewModel: MainScreenViewModel = hiltViewModel(), navController: NavHostController
) {
    MainScreen(
        uiState = viewModel.uiState.value,
        onEvent = viewModel::onEvent,
        navController = navController,
    )
}