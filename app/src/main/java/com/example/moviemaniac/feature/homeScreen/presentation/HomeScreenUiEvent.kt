package com.example.moviemaniac.feature.homeScreen.presentation

sealed class HomeScreenUiEvent {
    data class QueryChanged(val query: String) : HomeScreenUiEvent()
    data class MovieCardClicked(val movieId: Int) : HomeScreenUiEvent()
    data class PagerDotClicked(val page: Int) : HomeScreenUiEvent()
    data class PagerPageChanged(val page: Int) : HomeScreenUiEvent()
    data class ShowTypeChanged(val showType: ShowType) : HomeScreenUiEvent()
}