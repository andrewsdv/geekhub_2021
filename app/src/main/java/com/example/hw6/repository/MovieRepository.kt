package com.example.hw6.repository

import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MoviePreview
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun moviesFlow(): Flow<List<MoviePreview>>
    fun movieDetailsFlow(movieId: Int): Flow<MovieDetails?>
    suspend fun loadMovies(page: Int): List<MoviePreview>
    suspend fun loadMovieDetails(movieId: Int): MovieDetails?
}