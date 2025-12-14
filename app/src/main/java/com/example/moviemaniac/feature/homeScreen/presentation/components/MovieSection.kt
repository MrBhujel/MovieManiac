package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.ui.components.MovieRowWithStatus

@Composable
fun MovieSection(movieThumbnails: List<String>) {

    // Now Playing
    MovieRowWithStatus(
        statusName = "Now Playing",
        movieThumbnails = movieThumbnails,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )


    // Popular
    MovieRowWithStatus(
        statusName = "Popular",
        movieThumbnails = movieThumbnails,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )


    // Top Rated
    MovieRowWithStatus(
        statusName = "Top Rated",
        movieThumbnails = movieThumbnails,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )
}