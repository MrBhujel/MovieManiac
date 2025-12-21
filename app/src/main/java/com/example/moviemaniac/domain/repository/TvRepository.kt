package com.example.moviemaniac.domain.repository

import com.example.moviemaniac.domain.model.AiringTodayTv
import com.example.moviemaniac.domain.model.OnTheAirTv
import com.example.moviemaniac.domain.model.PopularTv
import com.example.moviemaniac.domain.model.TopRatedTv
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getAiringTodayTv(): Flow<List<AiringTodayTv>>

    fun getOnTheAirTv(): Flow<List<OnTheAirTv>>

    fun getPopularTv(): Flow<List<PopularTv>>

    fun getTopRatedTv(): Flow<List<TopRatedTv>>

}