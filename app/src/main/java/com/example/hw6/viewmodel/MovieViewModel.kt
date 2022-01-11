package com.example.hw6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.repository.MovieRepository

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private var _movieList = MutableLiveData<MovieList>()

    val movieList: LiveData<MovieList>
        get() = _movieList

    init {
        _movieList = repository.fetchMovieList()
        Log.e("list", _movieList.value.toString())
    }

    fun getMovieDetails(id: Int): LiveData<MovieDetails> {
        return repository.fetchMovieDetails(id)
    }

    fun fetchActorDetails(id: Int): LiveData<MovieCast> {
        return repository.fetchActorDetails(id)
    }
}