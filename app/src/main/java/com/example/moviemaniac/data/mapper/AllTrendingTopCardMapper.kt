package com.example.moviemaniac.data.mapper

import com.example.moviemaniac.data.model.AllTrendingTopCardDto
import com.example.moviemaniac.domain.model.AllTrendingTopCard

fun AllTrendingTopCardDto.toDomain(): AllTrendingTopCard = AllTrendingTopCard(
    id = id,
    movieTitle = movieTitle,
    tvTitle = tvTitle,
    overview = overview,
    rating = rating,
    posterPath = posterPath,
    mediaType = mediaType,
    movieReleaseDate = movieReleaseDate?.take(4),
    tvReleaseDate = tvReleaseDate?.take(4),
)