package com.example.moviemaniac.data.datasource.remote

import com.example.moviemaniac.core.network.TvApi
import com.example.moviemaniac.data.model.AiringTodayTvDto
import com.example.moviemaniac.data.model.OnTheAirTvDto
import com.example.moviemaniac.data.model.PopularTvDto
import com.example.moviemaniac.data.model.TopRatedTvDto
import javax.inject.Inject

class TvRemoteDataSource @Inject constructor(
    private val api: TvApi
) {

    suspend fun getOnTheAirTv(): List<OnTheAirTvDto> {
        val response = api.getOnTheAirTv()
        return response.results ?: emptyList()
    }

    suspend fun getAiringTodayTv(): List<AiringTodayTvDto> {
        val response = api.getAiringTodayTv()
        return response.results ?: emptyList()
    }

    suspend fun getPopularTv(): List<PopularTvDto> {
        val response = api.getPopularTv()
        return response.results ?: emptyList()
    }

    suspend fun getTopRatedTv(): List<TopRatedTvDto> {
        val response = api.getTopRatedTv()
        return response.results ?: emptyList()
    }
}