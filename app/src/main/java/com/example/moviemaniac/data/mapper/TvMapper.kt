package com.example.moviemaniac.data.mapper

import com.example.moviemaniac.data.model.AiringTodayTvDto
import com.example.moviemaniac.data.model.OnTheAirTvDto
import com.example.moviemaniac.data.model.PopularTvDto
import com.example.moviemaniac.data.model.TopRatedTvDto
import com.example.moviemaniac.domain.model.AiringTodayTv
import com.example.moviemaniac.domain.model.OnTheAirTv
import com.example.moviemaniac.domain.model.PopularTv
import com.example.moviemaniac.domain.model.TopRatedTv

fun AiringTodayTvDto.toDomain(): AiringTodayTv = AiringTodayTv(
    id = tvId,
    title = tvTitle,
    posterPath = posterPath,
    type = "Tv",
    releaseDate = releaseDate.take(4)
)

fun OnTheAirTvDto.toDomain(): OnTheAirTv = OnTheAirTv(
    id = tvId,
    title = tvTitle,
    posterPath = posterPath,
    type = "Tv",
    releaseDate = releaseDate.take(4)
)

fun PopularTvDto.toDomain(): PopularTv = PopularTv(
    id = tvId,
    title = tvTitle,
    posterPath = posterPath,
    type = "Tv",
    releaseDate = releaseDate.take(4)
)

fun TopRatedTvDto.toDomain(): TopRatedTv = TopRatedTv(
    id = tvId,
    title = tvTitle,
    posterPath = posterPath,
    type = "Tv",
    releaseDate = releaseDate.take(4)
)