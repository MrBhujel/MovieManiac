package com.example.moviemaniac.domain.repository

import com.example.moviemaniac.domain.model.NowPlayingMovie
import com.example.moviemaniac.domain.model.PopularMovie
import com.example.moviemaniac.domain.model.TopRatedMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<PopularMovie>>

    fun getNowPlayingMovies(): Flow<List<NowPlayingMovie>>

    fun getTopRatedMovies(): Flow<List<TopRatedMovie>>

}