package com.example.moviemaniac.core.network

import com.example.moviemaniac.data.model.AllTrendingTopCardResponseDto
import retrofit2.http.GET

interface AllTrendingApi {

    @GET("trending/all/week")
    suspend fun getAllTrending(): AllTrendingTopCardResponseDto
}