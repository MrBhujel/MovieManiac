package com.example.moviemaniac.feature.homeScreen.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviemaniac.feature.homeScreen.presentation.components.DisclaimerScreen
import com.example.moviemaniac.feature.homeScreen.presentation.components.MovieSection
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerDotIndicators
import com.example.moviemaniac.feature.homeScreen.presentation.components.PagerMovieCard
import com.example.moviemaniac.feature.homeScreen.presentation.components.ShowTypeChip
import com.example.moviemaniac.feature.homeScreen.presentation.components.TvSection

@Composable
fun HomeScreen(
    mainNavController: NavHostController,
    uiState: HomeScreenUiState,
    onEvent: (HomeScreenUiEvent) -> Unit,
    viewModel: HomeScreenViewModel
) {

    val context = LocalContext.current

    val popularMovies by viewModel.popularMovies.collectAsState(initial = emptyList())
    val nowPlayingMovies by viewModel.nowPlayingMovies.collectAsState(initial = emptyList())
    val topRatedMovies by viewModel.topRatedMovies.collectAsState(initial = emptyList())

    val airingTodayTv by viewModel.airingTodayTv.collectAsState(initial = emptyList())
    val onTheAirTv by viewModel.onTheAirTv.collectAsState(initial = emptyList())
    val popularTv by viewModel.popularTv.collectAsState(initial = emptyList())
    val topRatedTv by viewModel.topRatedTv.collectAsState(initial = emptyList())

    val allTrending by viewModel.allTrending.collectAsState(initial = emptyList())
    val limitedTrending = allTrending.take(10)


    var isAnimating by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(
        initialPage = uiState.currentHorizontalPagerPage,
        initialPageOffsetFraction = 0f,
        pageCount = { limitedTrending.size }
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
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Horizontal Pager
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HorizontalPager(
                    state = pagerState,
                ) { page ->
                    PagerMovieCard(
                        movie = allTrending[page],
                        modifier = Modifier
                            .clickable {
                                Toast.makeText(
                                    context,
                                    "${allTrending[page].tvTitle ?: allTrending[page].movieTitle} clicked",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    )
                }
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
                    popularMovies = popularMovies,
                    nowPlayingMovies = nowPlayingMovies,
                    topRatedMovies = topRatedMovies,
                    navController = mainNavController
                )
            } else {
                TvSection(
                    onTheAirTv = onTheAirTv,
                    airingTodayTv = airingTodayTv,
                    popularTv = popularTv,
                    topRatedTv = topRatedTv,
                    navController = mainNavController
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            DisclaimerScreen()
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewHomeScreen() {
//    val uiState = HomeScreenUiState("", 0)
//    val viewModel = HomeScreenViewModel()
//
//    HomeScreen(uiState, onEvent = {}, viewModel)
//}