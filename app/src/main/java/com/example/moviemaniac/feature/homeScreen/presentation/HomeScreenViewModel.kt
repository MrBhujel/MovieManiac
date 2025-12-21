package com.example.moviemaniac.feature.homeScreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.domain.repository.AllTrendingRepository
import com.example.moviemaniac.domain.repository.MovieRepository
import com.example.moviemaniac.domain.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val allTrendingRepository: AllTrendingRepository
) : ViewModel() {

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

    // Movies
    val popularMovies = movieRepository.getPopularMovies()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val nowPlayingMovies = movieRepository.getNowPlayingMovies()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val topRatedMovies = movieRepository.getTopRatedMovies()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Tv Shows
    val airingTodayTv = tvRepository.getAiringTodayTv()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val onTheAirTv = tvRepository.getOnTheAirTv()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val popularTv = tvRepository.getPopularTv()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val topRatedTv = tvRepository.getTopRatedTv()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // All Trending
    val allTrending = allTrendingRepository.getAllTrending()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.QueryChanged -> updateQueryChanged(event.query)
            is HomeScreenUiEvent.MovieCardClicked -> navigateToMovieDetails(event.movieId)
            is HomeScreenUiEvent.PagerDotClicked -> updateHorizontalPagerPage(event.page)
            is HomeScreenUiEvent.PagerPageChanged -> updateHorizontalPagerPage(event.page)
            is HomeScreenUiEvent.ShowTypeChanged -> updateShowType(event.showType)
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

    fun updateShowType(type: ShowType) {
        _uiState.value = uiState.value.copy(showType = type)
    }
}