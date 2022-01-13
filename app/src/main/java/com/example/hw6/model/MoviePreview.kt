package com.example.hw6.model

import com.example.hw6.database.entity.MoviePreviewEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviePreview(
    val id: Int,
    @Json(name = "poster_path")
    val posterAddress: String?,
    @Json(name = "vote_average")
    val rate: Double?
) {
    fun toDbEntity(): MoviePreviewEntity {
        return MoviePreviewEntity(id, posterAddress ?: "", rate ?: Double.NaN)
    }
}