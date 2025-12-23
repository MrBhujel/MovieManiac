package com.example.moviemaniac.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailScreenDto(

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("imdb_id")
    val imdbId: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val genres: List<GenreDto>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int)
