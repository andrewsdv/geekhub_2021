package com.example.hw6.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @PrimaryKey
    @ColumnInfo(name = "id")
    val movieId: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "budget")
    val budget: Int,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "revenue")
    val revenue: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
)