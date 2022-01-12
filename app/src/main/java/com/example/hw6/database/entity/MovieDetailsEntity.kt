package com.example.hw6.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hw6.model.MovieDetails

// Represents the SQLite table
@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    // Each property in the class represents a column in the table.
    //
    // Room will ultimately use these properties to both create the table and instantiate objects
    // from rows in the database.
    //
    // Every property that's stored in the database needs to have public visibility, which is
    // the Kotlin default.
    @ColumnInfo(name = "id")
    val movieId: Int? = null,
    @PrimaryKey
    @ColumnInfo(name = "original_title")
    val movieName: String,
    @ColumnInfo(name = "backdrop_path")
    val backgroundAddress: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val rate: Double,
) {
    fun toLocal(): MovieDetails {
        return MovieDetails(
            movieId,
            movieName,
            backgroundAddress,
            overview,
            emptyList(), // Not storing currently
            rate
        )
    }
}