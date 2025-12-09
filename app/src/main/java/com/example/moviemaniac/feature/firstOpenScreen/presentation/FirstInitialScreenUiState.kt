package com.example.moviemaniac.feature.firstOpenScreen.presentation

data class FirstInitialScreenUiState(
    val query: String = "",
    val searchResults: List<String> = emptyList(),
    val isHomeButtonClicked: Boolean = false
)