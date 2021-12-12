package com.example.hw6.service

import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/now_playing")
    fun groupList(): Call<MovieList>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Call<MovieDetails>

    @GET("movie/{id}/credits")
    fun getMovieCast(@Path("id") movieId: Int): Call<MovieCast>
}