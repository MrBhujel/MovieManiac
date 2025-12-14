package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.ui.components.MovieRowWithStatus

@Composable
fun TvSection(thumbNail: List<String>) {

    // Now Playing
    MovieRowWithStatus(
        statusName = "Now Playing",
        movieThumbnails = thumbNail,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )


    // Popular
    MovieRowWithStatus(
        statusName = "Popular",
        movieThumbnails = thumbNail,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )


    // Top Rated
    MovieRowWithStatus(
        statusName = "Top Rated",
        movieThumbnails = thumbNail,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )

}

@Preview(showBackground = true)
@Composable
private fun PreviewTvSection() {
    val thumbNail: List<String> = listOf()

    TvSection(thumbNail)
}