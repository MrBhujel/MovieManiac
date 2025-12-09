package com.example.moviemaniac.feature.firstOpenScreen.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.moviemaniac.feature.firstOpenScreen.presentation.FirstInitialScreen
import com.example.moviemaniac.feature.firstOpenScreen.presentation.FirstInitialScreenViewModel

@Composable
fun FirstInitialScreenRoute(
    viewModel: FirstInitialScreenViewModel = hiltViewModel()
) {
    FirstInitialScreen(
        viewModel.uiState.value,
        onEvent = viewModel::onEvent
    )
}