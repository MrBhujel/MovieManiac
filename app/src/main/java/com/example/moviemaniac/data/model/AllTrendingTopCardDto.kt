package com.example.moviemaniac.data.model

import com.google.gson.annotations.SerializedName

data class AllTrendingTopCardDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val movieTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("vote_average")
    val rating: String,

    @SerializedName("name")
    val tvTitle: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("media_type")
    val mediaType: String,

    @SerializedName("release_date")
    val movieReleaseDate: String?,

    @SerializedName("first_air_date")
    val tvReleaseDate: String?
)
