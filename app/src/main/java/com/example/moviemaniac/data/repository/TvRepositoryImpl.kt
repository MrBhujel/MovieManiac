package com.example.moviemaniac.data.repository

import com.example.moviemaniac.data.datasource.remote.TvRemoteDataSource
import com.example.moviemaniac.data.mapper.toDomain
import com.example.moviemaniac.domain.model.AiringTodayTv
import com.example.moviemaniac.domain.model.OnTheAirTv
import com.example.moviemaniac.domain.model.PopularTv
import com.example.moviemaniac.domain.model.TopRatedTv
import com.example.moviemaniac.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val remote: TvRemoteDataSource,
) : TvRepository {
    override fun getAiringTodayTv(): Flow<List<AiringTodayTv>> = flow {
        val dtoList = remote.getAiringTodayTv()
        emit(dtoList.map { it.toDomain() })
    }

    override fun getOnTheAirTv(): Flow<List<OnTheAirTv>> = flow {
        val dtoList = remote.getOnTheAirTv()
        emit(dtoList.map { it.toDomain() })
    }

    override fun getPopularTv(): Flow<List<PopularTv>> = flow {
        val dtoList = remote.getPopularTv()
        emit(dtoList.map { it.toDomain() })
    }

    override fun getTopRatedTv(): Flow<List<TopRatedTv>> = flow {
        val dtoList = remote.getTopRatedTv()
        emit(dtoList.map { it.toDomain() })
    }
}