package com.example.moviemaniac.data.mapper

import com.example.moviemaniac.data.model.MovieDetailScreenDto
import com.example.moviemaniac.domain.model.MovieDetailScreenModel

fun MovieDetailScreenDto.toDomain(): MovieDetailScreenModel = MovieDetailScreenModel(
    movieId = movieId,
    imdbId = imdbId,
    originalTitle = originalTitle,
    title = title,
    backdropPath = backdropPath,
    posterPath = posterPath,
    genre = genre,
    originalLanguage = originalLanguage,
    overview = overview,
    releaseDate = releaseDate.take(4),
    runtime = runtime,
    status = status,
    tagline = tagline,
    voteAverage = voteAverage,
    voteCount = voteCount
)