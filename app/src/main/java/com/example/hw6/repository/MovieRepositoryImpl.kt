package com.example.hw6.repository

import androidx.lifecycle.MutableLiveData
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.model.MoviePreview
import com.example.hw6.repository.datasource.LocalMovieDataSourceImpl
import com.example.hw6.repository.datasource.RemoteMovieDataSourceImpl
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val localMovieDataSource: LocalMovieDataSourceImpl,
    private val remoteMovieDataSource: RemoteMovieDataSourceImpl
) : MovieRepository {
//    override fun moviesFlow(): Flow<MovieList> =
//        remoteMovieDataSource.moviesFlow()
//
//    override fun movieDetailsFlow(movieId: Int): Flow<MovieDetails?> =
//        localMovieDataSource.movieDetailsFlow(movieId)
//
//    override fun actorDetailsFlow(movieId: Int): Flow<MovieCast?> {
//        localMovieDataSource.actorDetailsFlow(movieId)
//    }
//
//    override suspend fun loadMovies(page: Int): List<MoviePreview> =
//        remoteMovieDataSource.loadMovies(page).also { movies ->
//            localMovieDataSource.addMovies(movies)
//        }
//
//    override suspend fun loadMovieDetails(movieId: Int): MovieDetails? =
//        remoteMovieDataSource.loadMovieDetails(movieId)
//            ?.also { movieDetails -> localMovieDataSource.addMovieDetails(movieDetails) }


    override suspend fun fetchMovieList(): MutableLiveData<MovieList> =
        remoteMovieDataSource.loadMovieList().also { movies ->
            movies.value?.results?.let { localMovieDataSource.addMovies(it) }
        }

    override suspend fun fetchMovieDetails(id: Int): MutableLiveData<MovieDetails> =
        remoteMovieDataSource.loadMovieDetails(id)
            .also { movieDetails ->
                movieDetails.value?.let {
                    localMovieDataSource.addMovieDetails(
                        it
                    )
                }
            }


    override suspend fun fetchActorDetails(id: Int): MutableLiveData<MovieCast> =
        remoteMovieDataSource.loadActorDetails(id)
}