package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.domain.model.NowPlayingMovie
import com.example.moviemaniac.domain.model.PopularMovie
import com.example.moviemaniac.domain.model.TopRatedMovie
import com.example.moviemaniac.ui.components.MovieRowWithStatus

@Composable
fun MovieSection(
    popularMovies: List<PopularMovie>,
    nowPlayingMovies: List<NowPlayingMovie>,
    topRatedMovies: List<TopRatedMovie>
) {

    // Now Playing
    MovieRowWithStatus(
        statusName = "Now Playing",
        movies = nowPlayingMovies,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )


    // Popular
    MovieRowWithStatus(
        statusName = "Popular",
        movies = popularMovies,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )


    // Top Rated
    MovieRowWithStatus(
        statusName = "Top Rated",
        movies = topRatedMovies,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )
}