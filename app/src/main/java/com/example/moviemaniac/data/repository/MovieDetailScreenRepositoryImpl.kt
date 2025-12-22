package com.example.moviemaniac.data.repository

import com.example.moviemaniac.data.datasource.remote.MovieDetailScreenDataSource
import com.example.moviemaniac.data.mapper.toDomain
import com.example.moviemaniac.domain.model.MovieDetailScreenModel
import com.example.moviemaniac.domain.repository.MovieDetailScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailScreenRepositoryImpl @Inject constructor(
    private val remote: MovieDetailScreenDataSource
) : MovieDetailScreenRepository {
    override fun getMovieDetail(movieId: Int): Flow<MovieDetailScreenModel> = flow {
        val dto = remote.getMovieDetails(movieId = movieId)
        emit(dto.toDomain())
    }
}