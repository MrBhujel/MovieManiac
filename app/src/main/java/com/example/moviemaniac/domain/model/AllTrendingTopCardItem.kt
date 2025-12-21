package com.example.moviemaniac.domain.model

interface AllTrendingTopCardItem {
    val id: Int
    val movieTitle: String?
    val tvTitle: String?
    val overview: String
    val rating: String
    val posterPath: String
    val mediaType: String
    val movieReleaseDate: String?
    val tvReleaseDate: String?
}