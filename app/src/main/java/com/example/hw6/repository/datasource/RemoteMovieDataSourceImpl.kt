package com.example.hw6.repository.datasource

import com.example.hw6.ext.check
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.service.MovieService

class RemoteMovieDataSourceImpl(
    private val movieService: MovieService
) : MovieDataSource.Remote {

    override suspend fun loadMovieList(): MovieList =
        MovieList(movieService.groupList().check().body()?.results ?: emptyList())

    override suspend fun loadMovieDetails(id: Int): MovieDetails? =
        movieService.getMovieDetails(id).check().body()

    override suspend fun loadActorDetails(id: Int): MovieCast? =
        movieService.getMovieCast(id).check().body()
}