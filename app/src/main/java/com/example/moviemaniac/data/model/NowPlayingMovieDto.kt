package com.example.moviemaniac.data.model

import com.google.gson.annotations.SerializedName

data class NowPlayingMovieDto(
    @SerializedName("id")
    val movieId: Int,

    @SerializedName("title")
    val movieTitle: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String
)
