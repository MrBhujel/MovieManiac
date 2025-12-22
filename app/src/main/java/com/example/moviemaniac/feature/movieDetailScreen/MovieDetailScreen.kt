package com.example.moviemaniac.feature.movieDetailScreen

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.moviemaniac.domain.model.MovieDetailScreenModel

@Composable
fun MovieDetailScreen(
    movieDetail: MovieDetailScreenModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movieDetail.posterPath}",
                    contentDescription = "Movie poster",
                    modifier = Modifier.aspectRatio(2f / 3f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetailScreen() {
    MovieDetailScreen(
        movieDetail = MovieDetailScreenModel(
            movieId = 123,
            imdbId = "tt12345",
            originalTitle = "Original Title",
            title = "Title",
            backdropPath = "Backdrop Path",
            posterPath = "",
            genre = listOf(),
            originalLanguage = "",
            overview = "",
            releaseDate = "",
            runtime = 120,
            status = "",
            tagline = "",
            voteAverage = 8.5,
            voteCount = 123
        )
    )
}