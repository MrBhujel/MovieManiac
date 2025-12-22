package com.example.moviemaniac.core.network

import MovieDetailScreenResponseDto
import com.example.moviemaniac.data.model.MovieDetailScreenDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailScreenApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailScreenDto

}