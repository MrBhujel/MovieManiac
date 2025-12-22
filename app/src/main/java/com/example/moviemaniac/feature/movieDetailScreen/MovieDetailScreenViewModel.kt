package com.example.moviemaniac.feature.movieDetailScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.domain.model.MovieDetailScreenModel
import com.example.moviemaniac.domain.repository.MovieDetailScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailScreenViewModel @Inject constructor(
    private val repository: MovieDetailScreenRepository
) : ViewModel() {

    private var _uiState = mutableStateOf(
        MovieDetailScreenUiState(
            watchButtonClicked = true
        )
    )

    val uiState: State<MovieDetailScreenUiState> = _uiState

    private val _movieDetail =
        mutableStateOf<MovieDetailScreenModel?>(null)

    val movieDetail: State<MovieDetailScreenModel?> = _movieDetail

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetail(movieId)
                .collect {
                    _movieDetail.value = it
                }
        }
    }

}