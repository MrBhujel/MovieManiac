package com.example.moviemaniac.feature.searchScreen.presentation

sealed class SearchScreenUiEvent {
    data class QueryTyped(val query: String) : SearchScreenUiEvent()
}