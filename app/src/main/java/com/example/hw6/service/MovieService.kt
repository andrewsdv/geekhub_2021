package com.example.hw6.service

import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/now_playing")
    suspend fun groupList(): Response<MovieList>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int): Response<MovieDetails>

    @GET("movie/{id}/credits")
    suspend fun getMovieCast(@Path("id") movieId: Int): Response<MovieCast>
}