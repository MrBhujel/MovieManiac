package com.example.moviemaniac.data.datasource.remote

import android.util.Log
import com.example.moviemaniac.core.network.MovieApi
import com.example.moviemaniac.data.model.NowPlayingMovieDto
import com.example.moviemaniac.data.model.PopularMovieDto
import com.example.moviemaniac.data.model.TopRatedMovieDto
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: MovieApi
) {
    suspend fun getPopularMovies(): List<PopularMovieDto> {
        val response = api.getPopularMovies()
        Log.d("MovieRemote", "Popular Movies API Response: $response")
        return response.results ?: emptyList()
    }

    suspend fun getNowPlayingMovies(): List<NowPlayingMovieDto> {
        val response = api.getNowPlayingMovies()
        Log.d("MovieRemote", "Now Playing Movies API Response: $response")
        return response.results ?: emptyList()
    }

    suspend fun getTopRatedMovies(): List<TopRatedMovieDto> {
        val response = api.getTopRatedMovies()
        Log.d("MovieRemote", "Top Rated Movies API Response: $response")
        return response.results ?: emptyList()}
}