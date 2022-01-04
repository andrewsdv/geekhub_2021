package com.example.hw6.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hw6.model.MovieCast
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MovieList
import com.example.hw6.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    private val movieService: MovieService
) {

    fun fetchMovieList(): MutableLiveData<MovieList> {
        val list = MutableLiveData<MovieList>()

        movieService.groupList().enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    list.value = response.body() ?: MovieList(listOf())
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.e("Preview", t.toString())
            }
        })

        return list
    }

    fun fetchMovieDetails(id: Int): MutableLiveData<MovieDetails> {
        val details = MutableLiveData<MovieDetails>()

        movieService.getMovieDetails(id).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful) {
                    response.body()?.let { details.value = it }
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.e("details", t.message.toString())
            }
        })

        return details
    }

    fun fetchActorDetails(id: Int): MutableLiveData<MovieCast> {
        val details = MutableLiveData<MovieCast>()

        movieService.getMovieCast(id).enqueue(object : Callback<MovieCast> {
            override fun onResponse(call: Call<MovieCast>, response: Response<MovieCast>) {
                if (response.isSuccessful) {
                    response.body()?.let { details.value = it }
                }
            }

            override fun onFailure(call: Call<MovieCast>, t: Throwable) {
                Log.e("Actor", t.message.toString())
            }

        })

        return details
    }
}