package com.example.moviemaniac.domain.model

interface MovieItem {
    val id: Int
    val title: String
    val posterPath: String
    val type: String
    val releaseDate: String
}