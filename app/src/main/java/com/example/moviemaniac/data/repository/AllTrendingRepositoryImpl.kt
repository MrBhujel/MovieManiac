package com.example.moviemaniac.data.repository

import com.example.moviemaniac.data.datasource.remote.AllTrendingTopCardDataSource
import com.example.moviemaniac.data.mapper.toDomain
import com.example.moviemaniac.domain.model.AllTrendingTopCard
import com.example.moviemaniac.domain.repository.AllTrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllTrendingRepositoryImpl @Inject constructor(
    private val remote: AllTrendingTopCardDataSource
) : AllTrendingRepository {
    override fun getAllTrending(): Flow<List<AllTrendingTopCard>> = flow {
        val dtoList = remote.getAllTrending()
        emit(dtoList.map { it.toDomain() })
    }
}