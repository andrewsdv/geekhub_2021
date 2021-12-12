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
import com.example.hw6.viewmodel.MovieViewModel
import retrofit2.Call
import retrofit2.Response
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class PosterFragment(private val listener: PosterLoader) : Fragment(R.layout.poster_fragment) {
    private val viewModel: MovieViewModel by viewModels()
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
        configRecyclerView()
        setMovieListObserver()
    }

    fun setMovieListObserver() {
        viewModel.movieList.observe(this, { t -> t?.results?.let { adapter.setList(it) } })
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