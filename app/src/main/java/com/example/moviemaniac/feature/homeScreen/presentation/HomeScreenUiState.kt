package com.example.moviemaniac.feature.homeScreen.presentation

data class HomeScreenUiState(
    val query: String,
    val currentHorizontalPagerPage: Int,
    val showType: ShowType = ShowType.MOVIE
)

enum class ShowType {
    MOVIE,
    TV_SHOW
}