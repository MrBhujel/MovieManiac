package com.example.moviemaniac.feature.mainScreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    private var _uiState = mutableStateOf(
        MainScreenUiState(
            selectedRoute = ""
        )
    )

    val uiState: State<MainScreenUiState> = _uiState

    fun onEvent(event: MainScreenUiEvent) {
        when (event) {
            is MainScreenUiEvent.NavItemChanged -> updateNavItemChanged(event.selectedRoute)
            is MainScreenUiEvent.BottomNavItemClicked -> updateBottomNavItemClicked(event.isBottomNavItemClicked)
        }
    }

    private fun updateNavItemChanged(selectedRoute: String) {
        _uiState.value = _uiState.value.copy(selectedRoute = selectedRoute)
    }

    private fun updateBottomNavItemClicked(isBottomNavItemClicked: Boolean) {
        _uiState.value = _uiState.value.copy(isNavItemClicked = true)
    }

    fun resetBottomNavItemClicked() {
        _uiState.value = _uiState.value.copy(isNavItemClicked = false)
    }

}