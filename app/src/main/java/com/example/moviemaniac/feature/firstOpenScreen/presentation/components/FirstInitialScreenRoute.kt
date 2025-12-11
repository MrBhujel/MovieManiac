package com.example.moviemaniac.feature.firstOpenScreen.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviemaniac.feature.firstOpenScreen.presentation.FirstInitialScreen
import com.example.moviemaniac.feature.firstOpenScreen.presentation.FirstInitialScreenViewModel

@Composable
fun FirstInitialScreenRoute(
    viewModel: FirstInitialScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    FirstInitialScreen(
        uiState = viewModel.uiState.value,
        onEvent = viewModel::onEvent,
        navController = navController,
        viewModel = viewModel
    )
}