package com.example.moviemaniac.core.network

import com.example.moviemaniac.data.model.NowPlayingMovieListResponseDto
import com.example.moviemaniac.data.model.PopularMovieListResponseDto
import com.example.moviemaniac.data.model.TopRatedMovieListResponseDto
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieListResponseDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingMovieListResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): TopRatedMovieListResponseDto

}