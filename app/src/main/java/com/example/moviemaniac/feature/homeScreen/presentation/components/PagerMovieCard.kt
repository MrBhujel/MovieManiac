package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.moviemaniac.domain.model.AllTrendingTopCard

@Composable
fun PagerMovieCard(
    movie: AllTrendingTopCard,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 2f)
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Text section
            Box(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    // For rating and the title and type
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Rating Icon",
                                tint = Color.Red
                            )

                            // Rating
                            Text(
                                text = movie.rating
                                    .toDoubleOrNull()
                                    ?.let { String.format("%.1f", it) }
                                    ?: "0.0",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        // Title and type section
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                        ) {
                            // Title section
                            (movie.movieTitle ?: movie.tvTitle)?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            // Type section
                            Text(
                                text = movie.mediaType.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase() else it.toString()
                                },
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )

                            // Release date section
                            (movie.movieReleaseDate ?: movie.tvReleaseDate)?.let {
                                Text(
                                    text = it,
                                    color = MaterialTheme
                                        .colorScheme
                                        .onSurface
                                        .copy(alpha = 0.6f)
                                )
                            }
                        }
                    }

                    // Genre section
//                    Text(
//                        text = genre.joinToString(separator = ", "),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                    )

                    // Summary title and summary section
                    Text(
                        text = "SUMMARY",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )

                    // Summary
                    Text(
                        text = movie.overview,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight()
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = "Poster",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f)
                        .padding(top = 20.dp, end = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPagerMovieCard() {
    PagerMovieCard(
        movie = AllTrendingTopCard(
            id = 1,
            movieTitle = "Venom: Let There Be Carnage",
            tvTitle = null,
            overview = "Aghopia gadosih gadsoi j",
            rating = "8",
            posterPath = "",
            mediaType = "Movie",
            movieReleaseDate = "2021-10-03",
            tvReleaseDate = ""
        )
    )
}