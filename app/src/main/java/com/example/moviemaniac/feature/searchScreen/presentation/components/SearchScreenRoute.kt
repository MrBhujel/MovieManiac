package com.example.moviemaniac.feature.searchScreen.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.moviemaniac.feature.searchScreen.presentation.SearchScreen
import com.example.moviemaniac.feature.searchScreen.presentation.SearchScreenViewModel

@Composable
fun SearchScreenRoute(
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    SearchScreen(
        uiState = viewModel.uiState.value,
        onEvent = viewModel::onEvent,
        viewModel = viewModel
    )
}