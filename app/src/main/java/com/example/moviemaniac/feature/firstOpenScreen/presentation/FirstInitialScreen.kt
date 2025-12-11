package com.example.moviemaniac.feature.firstOpenScreen.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviemaniac.R
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.ui.components.CustomizableSearchBar
import com.example.moviemaniac.ui.components.MovieCardThumbnail

@Composable
fun FirstInitialScreen(
    uiState: FirstInitialScreenUiState,
    onEvent: (FirstInitialScreenEvent) -> Unit,
    navController: NavController,
    viewModel: FirstInitialScreenViewModel
) {

    val context = LocalContext.current

    val movieThumbnails = stringArrayResource(id = R.array.movie_thumbnails)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .heightIn(min = 0.dp, max = 500.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomizableSearchBar(
                        query = uiState.query,
                        onQueryChange = { onEvent(FirstInitialScreenEvent.QueryTyped(it)) },
                        onSearch = {},
                        searchResults = listOf("Avatar", "Breaking Bad", "Interstellar"),
                        onResultClick = { result ->
                            Toast.makeText(context, "$result clicked", Toast.LENGTH_SHORT).show()
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            onEvent(FirstInitialScreenEvent.HomeButtonClicked(true))
                        },
                        modifier = Modifier.padding(6.dp),
                    ) {
                        Text("Home")
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Recently Watched",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            items(movieThumbnails.size) { index ->
                val thumbnailUrl = movieThumbnails[index]
                MovieCardThumbnail(
                    title = "Movie ${index + 1}",
                    imageUrl = thumbnailUrl
                )
            }
        }
    }

    // Resetting the state after navigation back to this screen
    LaunchedEffect(uiState.isHomeButtonClicked) {
        if (uiState.isHomeButtonClicked) {

            // Resetting the state first
            viewModel.resetHomeButtonState()

            // Then navigating
            navController.navigate(NavRoutes.screenB)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewFirstInitialScreen() {

    val uiState = FirstInitialScreenUiState()
    val navController = NavController(LocalContext.current)
    val viewModel = FirstInitialScreenViewModel()

    FirstInitialScreen(
        uiState = uiState,
        onEvent = { },
        navController = navController,
        viewModel = viewModel
    )
}