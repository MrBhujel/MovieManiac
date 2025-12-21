package com.example.moviemaniac.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemaniac.core.database.entity.RecentlyWatchedMovieEntity

@Dao
interface RecentlyWatchedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: RecentlyWatchedMovieEntity)

    @Delete
    suspend fun delete(movie: RecentlyWatchedMovieEntity)

    @Query("SELECT * FROM recently_watched_movies")
    suspend fun getAll(): List<RecentlyWatchedMovieEntity>

}