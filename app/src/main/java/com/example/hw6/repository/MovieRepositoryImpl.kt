package com.example.hw6.repository

import android.util.Log
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.repository.datasource.LocalMovieDataSourceImpl
import com.example.hw6.repository.datasource.RemoteMovieDataSourceImpl
import kotlinx.coroutines.flow.first

class MovieRepositoryImpl(
    private val localMovieDataSource: LocalMovieDataSourceImpl,
    private val remoteMovieDataSource: RemoteMovieDataSourceImpl
) : MovieRepository {

    override suspend fun fetchMovieList(): MovieList =
        remoteMovieDataSource.loadMovieList().also { movies ->
            localMovieDataSource.addMovies(movies.results)
        }

    override suspend fun fetchMovieDetails(id: Int): MovieDetails? {
        val flow = localMovieDataSource.movieDetailsFlow(id)
        return if (flow.first() == null) remoteMovieDataSource.loadMovieDetails(id)
            .also { movieDetails ->
                movieDetails?.let {
                    localMovieDataSource.addMovieDetails(it)
                    Log.d("GeekHub", "Requested from network. Stored to DB")
                }
            }
        else {
            Log.d("GeekHub", "Taken from DB")
            flow.first()
        }
    }


    override suspend fun fetchActorDetails(id: Int): MovieCast? =
        remoteMovieDataSource.loadActorDetails(id)
}