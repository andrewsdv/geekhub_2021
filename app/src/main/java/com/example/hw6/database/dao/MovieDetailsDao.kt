package com.example.hw6.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw6.database.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDetailsDao {
    @Query("SELECT * FROM movie_details WHERE id=:movieId")
    abstract fun fetchMovieDetails(movieId: Int): Flow<MovieDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addMovieDetails(movieDetailsEntity: MovieDetailsEntity)
}