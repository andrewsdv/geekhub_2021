package com.example.hw6.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList(
    val results: List<MoviePreview>
)
