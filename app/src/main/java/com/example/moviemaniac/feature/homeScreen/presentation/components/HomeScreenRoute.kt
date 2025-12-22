package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.moviemaniac.feature.homeScreen.presentation.HomeScreen
import com.example.moviemaniac.feature.homeScreen.presentation.HomeScreenViewModel

@Composable
fun HomeScreenRoute(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    mainNavController: NavHostController
) {
    HomeScreen(
        mainNavController = mainNavController,
        uiState = viewModel.uiState.value,
        onEvent = viewModel::onEvent,
        viewModel = viewModel
    )
}