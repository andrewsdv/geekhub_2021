package com.example.hw6.repository

import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MoviePreview
import com.example.hw6.repository.datasource.LocalMovieDataSourceImpl
import com.example.hw6.repository.datasource.RemoteMovieDataSourceImpl
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val localMovieDataSource: LocalMovieDataSourceImpl,
    private val remoteMovieDataSource: RemoteMovieDataSourceImpl
) : MovieRepository {
    override fun moviesFlow(): Flow<List<MoviePreview>> =
        localMovieDataSource.moviesFlow()

    override fun movieDetailsFlow(movieId: Int): Flow<MovieDetails?> =
        localMovieDataSource.movieDetailsFlow(movieId)

    override suspend fun loadMovies(page: Int): List<MoviePreview> =
        remoteMovieDataSource.loadMovies(page).also { movies ->
            localMovieDataSource.addMovies(movies)
        }

    override suspend fun loadMovieDetails(movieId: Int): MovieDetails? =
        remoteMovieDataSource.loadMovieDetails(movieId)
            ?.also { movieDetails -> localMovieDataSource.addMovieDetails(movieDetails) }
}