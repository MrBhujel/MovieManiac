package com.example.moviemaniac.domain.model

data class AllTrendingTopCard(
    override val id: Int,
    override val movieTitle: String?,
    override val tvTitle: String?,
    override val overview: String,
    override val rating: String,
    override val posterPath: String,
    override val mediaType: String,
    override val movieReleaseDate: String?,
    override val tvReleaseDate: String?
): AllTrendingTopCardItem
