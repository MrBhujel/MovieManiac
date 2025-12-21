package com.example.moviemaniac.data.model

import com.google.gson.annotations.SerializedName

data class OnTheAirTvDto(
    @SerializedName("id")
    val tvId: Int,

    @SerializedName("name")
    val tvTitle: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("first_air_date")
    val releaseDate: String)
