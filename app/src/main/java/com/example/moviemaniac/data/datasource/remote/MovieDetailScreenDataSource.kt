package com.example.moviemaniac.data.datasource.remote

import com.example.moviemaniac.core.network.MovieDetailScreenApi
import com.example.moviemaniac.data.model.MovieDetailScreenDto
import javax.inject.Inject

class MovieDetailScreenDataSource @Inject constructor(
    private val api: MovieDetailScreenApi
) {
    suspend fun getMovieDetails(movieId: Int): MovieDetailScreenDto {
        return  api.getMovieDetails(movieId)
    }
}