package com.example.moviemaniac.domain.model

data class TopRatedMovie(
    override val id: Int,
    override val title: String,
    override val posterPath: String,
    override val type: String = "Movie",
    override val releaseDate: String
) : MovieItem