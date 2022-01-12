package com.example.hw6.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hw6.model.MoviePreview
import com.squareup.moshi.Json

@Entity(tableName = "movie")
data class MoviePreviewEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val posterAddress: String,
    @ColumnInfo(name = "vote_average")
    val rate: Double,
) {
    fun toLocal(): MoviePreview {
        return MoviePreview(
            id,
            posterAddress,
            rate
        )
    }
}