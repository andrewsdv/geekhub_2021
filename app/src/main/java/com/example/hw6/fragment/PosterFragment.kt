package com.example.hw6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.adapter.PosterAdapter
import com.example.hw6.adapter.PosterLoader
import com.example.hw6.databinding.PosterFragmentBinding
import com.example.hw6.decorator.PosterDecorator
import com.example.hw6.viewmodel.MovieViewModel
import androidx.fragment.app.viewModels

class PosterFragment(private val listener: PosterLoader) : Fragment(R.layout.poster_fragment) {
    private val viewModel: MovieViewModel by viewModels()
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

    private fun setMovieListObserver() {
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