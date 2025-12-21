package com.example.moviemaniac.feature.searchScreen.presentation

data class SearchScreenUiState(
    val query: String = "",
    val searchResults: List<String> = emptyList(),)