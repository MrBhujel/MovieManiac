package com.example.moviemaniac.core.network

import com.example.moviemaniac.data.model.AiringTodayTvListResponseDto
import com.example.moviemaniac.data.model.OnTheAirTvListResponseDto
import com.example.moviemaniac.data.model.PopularTvListResponseDto
import com.example.moviemaniac.data.model.TopRatedTvListResponseDto
import retrofit2.http.GET

interface TvApi {

    @GET("tv/airing_today")
    suspend fun getAiringTodayTv(): AiringTodayTvListResponseDto

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTv(): OnTheAirTvListResponseDto

    @GET("tv/popular")
    suspend fun getPopularTv(): PopularTvListResponseDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(): TopRatedTvListResponseDto
}