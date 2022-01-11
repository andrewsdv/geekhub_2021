package com.example.hw6.repository.datasource

import androidx.lifecycle.MutableLiveData
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.model.MoviePreview
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    interface Local {
        fun moviesFlow(): Flow<List<MoviePreview>>
        fun movieDetailsFlow(movieId: Int): Flow<MovieDetails?>
        suspend fun addMovies(movies: List<MoviePreview>)
        suspend fun addMovieDetails(movieDetailsDomain: MovieDetails)
    }

    interface Remote {
        fun fetchMovieList(): MutableLiveData<MovieList>
        fun fetchMovieDetails(id: Int): MutableLiveData<MovieDetails>
        fun fetchActorDetails(id: Int): MutableLiveData<MovieCast>
    }
}