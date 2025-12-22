package com.example.moviemaniac.domain.repository

import com.example.moviemaniac.domain.model.MovieDetailScreenModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailScreenRepository {
    fun getMovieDetail(movieId: Int): Flow<MovieDetailScreenModel>
}