package com.example.moviemaniac.domain.model

data class AiringTodayTv(
    override val id: Int,
    override val title: String,
    override val posterPath: String,
    override val type: String = "Tv",
    override val releaseDate: String
) : MovieItem
