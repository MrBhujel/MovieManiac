package com.example.moviemaniac.feature.homeScreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private var _uiState = mutableStateOf(
        HomeScreenUiState(
            query = "",
            currentHorizontalPagerPage = 0
        )
    )

    val uiState: State<HomeScreenUiState> = _uiState

    private var autoSwipeJob: Job? = null

    var isInternalPageChange = false

    init {
        startAutoSwipe()
    }

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.QueryChanged -> updateQueryChanged(event.query)
            is HomeScreenUiEvent.MovieCardClicked -> navigateToMovieDetails(event.movieId)
            is HomeScreenUiEvent.PagerDotClicked -> updateHorizontalPagerPage(event.page)
            is HomeScreenUiEvent.PagerPageChanged -> updateHorizontalPagerPage(event.page)
        }
    }

    private fun updateQueryChanged(query: String) {}

    private fun navigateToMovieDetails(movieId: Int) {}

    private fun updateHorizontalPagerPage(page: Int) {
        isInternalPageChange = true
        _uiState.value = _uiState.value.copy(currentHorizontalPagerPage = page)
        restartAutoSwipe() // Reset the auto swipe when manually swiping
    }

    private fun restartAutoSwipe() {
        // Canceling the previous job and starting new one
        autoSwipeJob?.cancel()
        startAutoSwipe()
    }

    // Automatic page change every 3 second with looping
    private fun startAutoSwipe() {
        autoSwipeJob = viewModelScope.launch {
            while (true) {
                delay(3000)
                val pageCount = 10
                val nextPage = (_uiState.value.currentHorizontalPagerPage + 1) % pageCount
                isInternalPageChange = true
                _uiState.value = _uiState.value.copy(currentHorizontalPagerPage = nextPage)
            }
        }
    }
}