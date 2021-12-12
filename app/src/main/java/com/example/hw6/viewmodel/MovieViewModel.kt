package com.example.hw6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6.client.ApiClient
import com.example.hw6.model.MovieList
import com.example.hw6.service.MovieService
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var _movieList = MutableLiveData<MovieList>()
    private val movieService = ApiClient().retrofit.create(MovieService::class.java)

    val movieList: LiveData<MovieList>
        get() = _movieList

    init {
        fetchMovieList()
    }

    private fun fetchMovieList() {
        movieService.groupList().enqueue(object : retrofit2.Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    _movieList.value = response.body() ?: MovieList(listOf())
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.e("Preview", t.toString())
                return
            }
        })
    }
}