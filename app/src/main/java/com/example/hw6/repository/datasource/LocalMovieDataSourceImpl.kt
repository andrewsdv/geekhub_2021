package com.example.hw6.repository.datasource

import com.example.hw6.database.dao.MovieDetailsDao
import com.example.hw6.database.dao.MoviePreviewsDao
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MoviePreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// The DAO is passed into the repository constructor as opposed to the whole database. This is
// because it only needs access to the DAO, since the DAO contains all the read/write methods for
// the database. There's no need to expose the entire database to the repository.
class LocalMovieDataSourceImpl(
    private val moviesDao: MoviePreviewsDao,
    private val movieDetailsDao: MovieDetailsDao
) : MovieDataSource.Local {
    override fun moviesFlow(): Flow<List<MoviePreview>> =
        moviesDao.fetchMovies().map { it.map { it.toLocal() } }

    override fun movieDetailsFlow(movieId: Int): Flow<MovieDetails?> =
        movieDetailsDao.fetchMovieDetails(movieId)
            .map { it?.toLocal() }

    // The suspend modifier tells the compiler that this needs to be called from a coroutine or
    // another suspending function.
    //Room executes suspend queries off the main thread.
    override suspend fun addMovies(movies: List<MoviePreview>) =
        moviesDao.addMovies(movies.map { it.toDbEntity() })

    override suspend fun addMovieDetails(movieDetails: MovieDetails) =
        movieDetailsDao.addMovieDetails(movieDetails.toDbEntity())

}