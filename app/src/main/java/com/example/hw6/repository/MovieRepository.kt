package com.example.hw6.repository

import androidx.lifecycle.MutableLiveData
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList

interface MovieRepository {
    suspend fun fetchMovieList(): MutableLiveData<MovieList>
    suspend fun fetchMovieDetails(id: Int): MutableLiveData<MovieDetails>
    suspend fun fetchActorDetails(id: Int): MutableLiveData<MovieCast>
}