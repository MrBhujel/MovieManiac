package com.example.moviemaniac.feature.firstOpenScreen.presentation

sealed class FirstInitialScreenEvent {
    data class QueryTyped(val query: String): FirstInitialScreenEvent()
    data class HomeButtonClicked(val isHomeButtonClicked: Boolean): FirstInitialScreenEvent()
}