package com.example.moviemaniac.data.repository

import com.example.moviemaniac.data.datasource.remote.MovieRemoteDataSource
import com.example.moviemaniac.data.mapper.toDomain
import com.example.moviemaniac.domain.model.NowPlayingMovie
import com.example.moviemaniac.domain.model.PopularMovie
import com.example.moviemaniac.domain.model.TopRatedMovie
import com.example.moviemaniac.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteDataSource,
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<PopularMovie>> = flow {
        val dtoList = remote.getPopularMovies()
        emit(dtoList.map { it.toDomain() })
    }

    override fun getNowPlayingMovies(): Flow<List<NowPlayingMovie>> = flow {
        val dtoList = remote.getNowPlayingMovies()
        emit(dtoList.map { it.toDomain() })
    }

    override fun getTopRatedMovies(): Flow<List<TopRatedMovie>> = flow {
        val dtoList = remote.getTopRatedMovies()
        emit(dtoList.map { it.toDomain() })
    }

}