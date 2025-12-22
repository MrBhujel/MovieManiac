package com.example.moviemaniac.feature.movieDetailScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.moviemaniac.feature.movieDetailScreen.MovieDetailScreen
import com.example.moviemaniac.feature.movieDetailScreen.MovieDetailScreenViewModel

@Composable
fun MovieDetailScreenRoute(
    movieId: Int,
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetail(movieId)
    }

    val movieDetail = viewModel.movieDetail.value

    movieDetail?.let {
        MovieDetailScreen(movieDetail = it)
    }
}