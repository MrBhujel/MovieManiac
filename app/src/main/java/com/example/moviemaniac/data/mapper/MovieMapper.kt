package com.example.moviemaniac.data.mapper

import com.example.moviemaniac.data.model.NowPlayingMovieDto
import com.example.moviemaniac.data.model.PopularMovieDto
import com.example.moviemaniac.data.model.TopRatedMovieDto
import com.example.moviemaniac.domain.model.NowPlayingMovie
import com.example.moviemaniac.domain.model.PopularMovie
import com.example.moviemaniac.domain.model.TopRatedMovie

fun PopularMovieDto.toDomain(): PopularMovie = PopularMovie(
    id = movieId,
    title = movieTitle,
    posterPath = posterPath,
    type = "Movie",
    releaseDate = releaseDate.take(4)
)

fun NowPlayingMovieDto.toDomain(): NowPlayingMovie = NowPlayingMovie(
    id = movieId,
    title = movieTitle,
    posterPath = posterPath,
    type = "Movie",
    releaseDate = releaseDate.take(4)
)

fun TopRatedMovieDto.toDomain(): TopRatedMovie = TopRatedMovie(
    id = movieId,
    title = movieTitle,
    posterPath = posterPath,
    type = "Movie",
    releaseDate = releaseDate.take(4)
)