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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.R
import com.example.moviemaniac.feature.homeScreen.presentation.components.MovieSection
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerDotIndicators
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerMovieCard
import com.example.moviemaniac.feature.homeScreen.presentation.components.ShowTypeChip
import com.example.moviemaniac.feature.homeScreen.presentation.components.TvSection

@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    onEvent: (HomeScreenUiEvent) -> Unit,
    viewModel: HomeScreenViewModel
) {

    var isAnimating by remember { mutableStateOf(false) }

    val movieThumbnails = stringArrayResource(id = R.array.movie_thumbnails).toList()

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

        item {
            ShowTypeChip(
                selected = uiState.showType,
                onSelectedChange = {
                    onEvent(HomeScreenUiEvent.ShowTypeChanged(it))
                }
            )
        }

        item {

            if (uiState.showType == ShowType.MOVIE) {
                MovieSection(
                    movieThumbnails = movieThumbnails
                )
            } else {
                TvSection(
                    thumbNail = movieThumbnails
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