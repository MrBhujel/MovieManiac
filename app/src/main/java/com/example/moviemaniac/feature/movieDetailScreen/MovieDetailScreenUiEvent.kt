package com.example.moviemaniac.feature.movieDetailScreen

sealed class MovieDetailScreenUiEvent {
    data class WatchButtonClicked(val clicked: Boolean): MovieDetailScreenUiEvent()
}