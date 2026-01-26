package com.example.moviemaniac.feature.movieDetailScreen

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.domain.model.MovieDetailScreenModel
import com.example.moviemaniac.feature.homeScreen.presentation.components.DisclaimerScreen
import com.example.moviemaniac.feature.movieDetailScreen.component.GenreBackground
import java.util.Locale

@Composable
fun MovieDetailScreen(
    movieDetail: MovieDetailScreenModel,
    mainNavController: NavHostController
) {
    val scrollState = rememberLazyListState()
    val showTopBar by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex > 0 }
    }
    
    val formattedVoteAverage = String.format(Locale.ROOT, "%.1f", movieDetail.voteAverage)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header Image Section with Gradient Overlay
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original${movieDetail.backdropPath}",
                        contentDescription = "BackDrop Poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Sophisticated Gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                                        MaterialTheme.colorScheme.background
                                    )
                                )
                            )
                    )

                    // Title and Tagline floating over backdrop
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = movieDetail.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 40.sp
                        )
                        if (movieDetail.tagline.isNotEmpty()) {
                            Text(
                                text = movieDetail.tagline,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White.copy(alpha = 0.8f),
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }

            // Quick Info Bar (Rating, Year, Duration)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoItem(icon = Icons.Default.Star, text = formattedVoteAverage, color = Color(0xFFFFC107))
                    InfoItem(icon = Icons.Default.CalendarToday, text = movieDetail.releaseDate.take(4))
                    InfoItem(icon = Icons.Default.AccessTime, text = "${movieDetail.runtime} min")
                }
            }

            // Action Buttons
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            val movieUrl = "https://vidsrc.cc/v2/embed/movie/${movieDetail.movieId}"
                            val encodeUrl = Uri.encode(movieUrl)
                            mainNavController.navigate("${NavRoutes.mediaPlayerScreen}?videoUrl=$encodeUrl")
                        },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(28.dp), tint = Color.White)
                        Spacer(Modifier.width(8.dp))
                        Text("Watch Now", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            // Genres Chips
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    movieDetail.genres.take(3).forEach { genre ->
                        GenreBackground(genre.name)
                    }
                }
            }

            // Detailed Content Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Storyline",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = movieDetail.overview,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 24.sp,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Justify
                        )
                        
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 20.dp),
                            thickness = 0.5.dp,
                            color = Color.White.copy(alpha = 0.1f)
                        )
                        
                        DetailRow(label = "Original Title", value = movieDetail.originalTitle)
                        DetailRow(label = "Original Language", value = movieDetail.originalLanguage.uppercase())
                    }
                }
            }

            // Disclaimer Section
            item {
                DisclaimerScreen(modifier = Modifier.padding(bottom = 32.dp))
            }
        }

        // Floating Back Button
        IconButton(
            onClick = { mainNavController.popBackStack() },
            modifier = Modifier
                .padding(top = 48.dp, start = 16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f)),
            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        // Floating Top Bar (appears on scroll)
        AnimatedVisibility(
            visible = showTopBar,
            enter = fadeIn() + slideInVertically(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.95f))
                    .padding(top = 48.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { mainNavController.popBackStack() }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, color: Color = Color.Gray) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = color)
        Spacer(Modifier.width(6.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = Color.White)
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White.copy(alpha = 0.5f), style = MaterialTheme.typography.bodyMedium)
        Text(value, fontWeight = FontWeight.Medium, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetailScreen() {
    val mainNavController = rememberNavController()
    MovieDetailScreen(
        movieDetail = MovieDetailScreenModel(
            movieId = 123,
            imdbId = "tt12345",
            originalTitle = "The Godfather",
            title = "The Godfather",
            backdropPath = "",
            posterPath = "",
            genres = listOf(),
            originalLanguage = "en",
            overview = "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
            releaseDate = "1972-03-14",
            runtime = 175,
            status = "Released",
            tagline = "An offer you can't refuse.",
            voteAverage = 9.2,
            voteCount = 18000
        ),
        mainNavController = mainNavController
    )
}