package com.example.moviemaniac.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.domain.model.MovieItem

@Composable
fun MovieRowWithStatus(
    statusName: String,
    movies: List<MovieItem>,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = statusName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // View more text button
            if (statusName != "Now Playing") {
                TextButton(
                    onClick = {},
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "View More",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }
        }

        // Movie Section
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            items(movies) { movie ->
                MovieCardThumbnail(
                    title = movie.title,
                    imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    cardWidth = 150,
                    type = movie.type,
                    year = movie.releaseDate,
                    modifier = Modifier
                        .clickable {
                            Toast.makeText(context, "${movie.title} clicked!!!", Toast.LENGTH_SHORT)
                                .show()
                        }
                )
            }
        }
    }

}