package com.example.moviemaniac.data.datasource.remote

import com.example.moviemaniac.core.network.AllTrendingApi
import com.example.moviemaniac.data.model.AllTrendingTopCardDto
import javax.inject.Inject

class AllTrendingTopCardDataSource @Inject constructor(
    private val api: AllTrendingApi
) {
    suspend fun getAllTrending(): List<AllTrendingTopCardDto> {
        val response = api.getAllTrending()
        return response.results ?: emptyList()
    }
}