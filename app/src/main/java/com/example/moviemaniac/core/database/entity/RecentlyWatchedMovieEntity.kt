package com.example.moviemaniac.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recently_watched_movies")
data class RecentlyWatchedMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val showType: String,
    val releaseYear: String
)
