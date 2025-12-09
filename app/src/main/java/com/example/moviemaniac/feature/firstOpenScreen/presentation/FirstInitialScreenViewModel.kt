package com.example.moviemaniac.feature.firstOpenScreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstInitialScreenViewModel @Inject constructor() : ViewModel() {

    private var _uiState = mutableStateOf(
        FirstInitialScreenUiState(
            query = "",
            searchResults = listOf("avatar", "breaking bad", "interstellar")
        )
    )
    val uiState: State<FirstInitialScreenUiState> = _uiState

    fun onEvent(event: FirstInitialScreenEvent) {
        when (event) {
            is FirstInitialScreenEvent.QueryTyped -> updateQueryChanged(event.query)
            is FirstInitialScreenEvent.HomeButtonClicked -> navigateToHome(event.isHomeButtonClicked)
        }
    }

    private fun updateQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    private fun navigateToHome(isHomeButtonClicked: Boolean) {
        _uiState.value = _uiState.value.copy(isHomeButtonClicked = isHomeButtonClicked)
    }
}