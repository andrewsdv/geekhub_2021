package com.example.hw6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepositoryImpl
) : ViewModel() {
    private var _movieList = MutableLiveData<MovieList>()

    val movieList: LiveData<MovieList>
        get() = _movieList

    init {
        viewModelScope.launch() {
            _movieList.value = repository.fetchMovieList()
        }
    }

    /*
    * LiveData in an app's architecture
    * LiveData is lifecycle-aware, following the lifecycle of entities such as activities and
    *  fragments. Use LiveData to communicate between these lifecycle owners and other objects with
    *  a different lifespan, such as ViewModel objects. The main responsibility of the ViewModel is
    *  to load and manage UI-related data, which makes it a great candidate for holding LiveData
    *  objects. Create LiveData objects in the ViewModel and use them to expose state to the UI
    *  layer.
    * Activities and fragments should not hold LiveData instances because their role is to display
    *  data, not hold state. Also, keeping activities and fragments free from holding data makes it
    *  easier to write unit tests.
    *
    * LiveData objects should not live in the repository.
    * */
    suspend fun getMovieDetails(id: Int): MutableLiveData<MovieDetails?> {
        val data = MutableLiveData<MovieDetails?>()
        data.value = repository.fetchMovieDetails(id)
        return data
    }

    suspend fun fetchActorDetails(id: Int): MutableLiveData<MovieCast> {
        val data = MutableLiveData<MovieCast>()
        data.value = repository.fetchActorDetails(id)
        return data
    }
}