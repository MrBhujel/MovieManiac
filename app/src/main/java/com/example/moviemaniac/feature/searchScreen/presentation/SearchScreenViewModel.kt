package com.example.moviemaniac.feature.searchScreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = mutableStateOf(
        SearchScreenUiState(
            query = "",
            searchResults = listOf("avatar", "breaking bad", "interstellar")
        )
    )

    val uiState: State<SearchScreenUiState> = _uiState

    fun onEvent(event: SearchScreenUiEvent) {
        when (event) {
            is SearchScreenUiEvent.QueryTyped -> updateQueryChanged(event.query)
        }
    }

    private fun updateQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }
}