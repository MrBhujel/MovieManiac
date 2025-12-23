package com.example.moviemaniac.feature.movieDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.moviemaniac.domain.model.MovieDetailScreenModel
import com.example.moviemaniac.feature.homeScreen.presentation.components.DisclaimerScreen
import com.example.moviemaniac.feature.movieDetailScreen.component.GenreBackground

@Composable
fun MovieDetailScreen(
    movieDetail: MovieDetailScreenModel
) {

    val formattedVoteAverage = String.format("%.1f", movieDetail.voteAverage)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        item {

            val posterWidth = 200.dp
            val posterAspectRatio = 2f / 3f
            val posterHeight = posterWidth / posterAspectRatio
            val headerHeight = posterHeight + 80.dp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movieDetail.backdropPath}",
                    contentDescription = "BackDrop Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(posterHeight)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movieDetail.posterPath}",
                    contentDescription = "Poster Image",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(posterWidth)
                        .aspectRatio(posterAspectRatio)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        // Release Date, Run Time, Rating, genre and language
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                // Release Date, Run time and Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    // Release Date
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CalendarToday,
                            contentDescription = "Release Date",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .size(13.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = movieDetail.releaseDate,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Icon(
                        Icons.Default.Circle,
                        contentDescription = "Pointer",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.size(6.dp)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    // Run Time
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = "Runtime",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(13.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "${movieDetail.runtime}m",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Icon(
                        Icons.Default.Circle,
                        contentDescription = "Pointer",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.size(6.dp)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(13.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = formattedVoteAverage,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp
                        )
                    }
                }

                // Genre
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(movieDetail.genres.size) { index ->
                        GenreBackground(movieDetail.genres[index].name)

                        if (index < movieDetail.genres.size - 1) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }

                // Language
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Original Language: ",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 10.sp
                    )
                    GenreBackground(movieDetail.originalLanguage)
                }
            }
        }

        // Title
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title
                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = movieDetail.tagline,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Original Title
                Text(
                    text = "Original Title: ${movieDetail.originalTitle}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Watch Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 17.dp,
                                top = 2.dp,
                                bottom = 2.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "Play Icon",
                            tint = Color.White
                        )
                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                                .displayCutoutPadding()
                        )
                        Text(
                            text = "Play",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }

        // Overview
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = movieDetail.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Justify
                )
            }
        }

        // Disclaimer Section
        item {
            DisclaimerScreen(
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
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
            genres = listOf(),
            originalLanguage = "en",
            overview = "This is the overview of the movie that we want to see.",
            releaseDate = "2025",
            runtime = 120,
            status = "",
            tagline = "This is the best tagline ever",
            voteAverage = 8.5,
            voteCount = 123
        )
    )
}