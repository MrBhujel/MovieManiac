package com.example.moviemaniac.feature.homeScreen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerDotIndicators
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerMovieCard

@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    onEvent: (HomeScreenUiEvent) -> Unit,
    viewModel: HomeScreenViewModel
) {

    var isAnimating  by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(
        initialPage = uiState.currentHorizontalPagerPage,
        initialPageOffsetFraction = 0f,
        pageCount = { 10 }
    )

    // Syncing the pagerState with the uiState
    LaunchedEffect(uiState.currentHorizontalPagerPage) {
        isAnimating = true
        pagerState.animateScrollToPage(uiState.currentHorizontalPagerPage)
        isAnimating = false
    }

    LaunchedEffect(pagerState.currentPage) {
        if (!isAnimating && !viewModel.isInternalPageChange) {
            onEvent(HomeScreenUiEvent.PagerPageChanged(pagerState.currentPage))
        }
        viewModel.isInternalPageChange = false
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        // Horizontal Pager
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HorizontalPager(
                    state = pagerState,
                ) {
                    PagerMovieCard("", listOf())
                }

                PagerDotIndicators(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp),
                    onDotClick = { page ->
                        onEvent(HomeScreenUiEvent.PagerDotClicked(page))
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    val uiState = HomeScreenUiState("", 0)
    val viewModel = HomeScreenViewModel()

    HomeScreen(uiState, onEvent = {}, viewModel)
}