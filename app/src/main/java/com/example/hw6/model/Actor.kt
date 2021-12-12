package com.example.hw6.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Actor(
    val name: String,
    val character: String,
    @Json(name="profile_path")
    val photoAddress: String?,
    @Json(name="known_for_department")
    val department: String
)