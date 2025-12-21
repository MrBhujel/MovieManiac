package com.example.moviemaniac.domain.repository

import com.example.moviemaniac.domain.model.AllTrendingTopCard
import kotlinx.coroutines.flow.Flow

interface AllTrendingRepository {
    fun getAllTrending(): Flow<List<AllTrendingTopCard>>
}