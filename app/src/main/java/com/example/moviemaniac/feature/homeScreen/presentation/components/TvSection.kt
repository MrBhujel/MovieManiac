package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.domain.model.AiringTodayTv
import com.example.moviemaniac.domain.model.OnTheAirTv
import com.example.moviemaniac.domain.model.PopularTv
import com.example.moviemaniac.domain.model.TopRatedTv
import com.example.moviemaniac.ui.components.MovieRowWithStatus

@Composable
fun TvSection(
    onTheAirTv: List<OnTheAirTv>,
    airingTodayTv: List<AiringTodayTv>,
    popularTv: List<PopularTv>,
    topRatedTv: List<TopRatedTv>
) {

    // Airing Today
    MovieRowWithStatus(
        statusName = "Airing Today",
        movies = airingTodayTv,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )


    // On The Air
    MovieRowWithStatus(
        statusName = "On The Air",
        movies = onTheAirTv,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )


    // Popular
    MovieRowWithStatus(
        statusName = "Popular",
        movies = popularTv,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )


    // Top Rated
    MovieRowWithStatus(
        statusName = "Top Rated",
        movies = topRatedTv,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    )

}