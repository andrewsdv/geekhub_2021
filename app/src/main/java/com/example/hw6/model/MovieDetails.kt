package com.example.hw6.model

import com.example.hw6.database.entity.MovieDetailsEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    @Json(name = "backdrop_path")
    val backgroundAddress: String?,
    @Json(name = "original_title")
    val movieName: String,
    val overview: String?,
    val genres: List<Genre>,
    @Json(name = "vote_average")
    val rate: Double?
) {
    fun toDbEntity(): MovieDetailsEntity {
        return MovieDetailsEntity(
            null,
            backgroundAddress ?: "",
            movieName,
            overview ?: "",
            rate ?: Double.NaN)
    }
}