package com.example.moviemaniac.feature.searchScreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.ui.components.CustomizableSearchBar
import com.example.moviemaniac.ui.components.ParticleBackground

@Composable
fun SearchScreen(
    uiState: SearchScreenUiState,
    onEvent: (SearchScreenUiEvent) -> Unit,
    viewModel: SearchScreenViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Search Bar and Text
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomizableSearchBar(
                query = uiState.query,
                onQueryChange = { onEvent(SearchScreenUiEvent.QueryTyped(it)) },
                onSearch = {},
                searchResults = listOf("Avatar", "Breaking Bad", "Avatar", "Interstellar"),
                onResultClick = {}
            )
        }
    }
}