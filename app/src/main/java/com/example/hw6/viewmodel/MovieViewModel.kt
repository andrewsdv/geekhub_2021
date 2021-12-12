package com.example.hw6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6.model.MovieList

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private var _movieList = MutableLiveData<MovieList>()

    val movieList: LiveData<MovieList>
        get() = _movieList

    init {
        _movieList = repository.fetchMovieList()
        Log.e("list", _movieList.value.toString())
    }


}