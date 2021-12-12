package com.example.hw6.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.adapter.PosterAdapter
import com.example.hw6.adapter.PosterLoader
import com.example.hw6.client.ApiClient
import com.example.hw6.databinding.PosterFragmentBinding
import com.example.hw6.decorator.PosterDecorator
import com.example.hw6.model.MovieList
import com.example.hw6.service.MovieService
import retrofit2.Call
import retrofit2.Response

class PosterFragment(private val listener: PosterLoader) : Fragment(R.layout.poster_fragment) {
    private val movieService = ApiClient().retrofit.create(MovieService::class.java)
    private var movieList = MovieList(listOf())
    private lateinit var binding: PosterFragmentBinding
    private val adapter = PosterAdapter(listener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = PosterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMoviePreviews()
        configRecyclerView()
    }


    private fun fetchMoviePreviews() {
        movieService.groupList().enqueue(object : retrofit2.Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    movieList = response.body() ?: MovieList(listOf())
                    adapter.setList(movieList.results)
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.e("Preview", t.toString())
                return
            }
        })
    }

    private fun configRecyclerView() {
        binding.postersRecyclerView.apply {
            layoutManager = GridLayoutManager(context,
                2, RecyclerView.VERTICAL, false)
            this.adapter = this@PosterFragment.adapter
            addItemDecoration(PosterDecorator(40))
        }
    }
}