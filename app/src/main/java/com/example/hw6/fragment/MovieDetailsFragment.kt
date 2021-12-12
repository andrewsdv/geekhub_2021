package com.example.hw6.fragment

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hw6.R
import com.example.hw6.adapter.ActorAdapter
import com.example.hw6.databinding.DetailsFragmentBinding
import com.example.hw6.decorator.ActorDecorator
import com.example.hw6.helper.ProgressBarHelper
import com.example.hw6.model.MovieDetails
import com.example.hw6.model.MoviePreview
import com.example.hw6.viewmodel.MovieViewModel


class MovieDetailsFragment(private val moviePreview: MoviePreview) : Fragment(R.layout.details_fragment) {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding
    private val adapter = ActorAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovieDetails()
        configActorRecycler()
    }

    private fun fetchMovieDetails() {
        viewModel.getMovieDetails(moviePreview.id).observe(this, { t -> t?.let { updateUI(it) } })
    }

    private fun updateUI(details: MovieDetails) {
        binding.apply {

            filmNameTextView.text = details.movieName
            filmDescriptionView.text = details.overview
            filmCategoriesTextView.text = details.genres.joinToString { it.name }

            details.rate.let {
                val intRate = (it * 10).toInt()

                val unwrappedDrawable = context?.let { it1 ->
                    AppCompatResources.getDrawable(it1, R.drawable.progress_circle) } as LayerDrawable?

                unwrappedDrawable?.let { drawable ->
                    DrawableCompat.setTint(drawable, ProgressBarHelper.getProgressDrawableColor(it))

                    drawable.findDrawableByLayerId(R.id.progressBackground)?.let { it1 ->
                         DrawableCompat.setTint(it1, Color.BLACK)
                    }
                }

                percentsTextView.text = String.format(resources.getString(R.string.percents), (intRate.toString()))
                filmRateProgressBar.progressDrawable = unwrappedDrawable
                filmRateProgressBar.progress = intRate
            }

            context?.let {
                Glide.with(it)
                    .load("https://image.tmdb.org/t/p/w1280" + details.backgroundAddress)
                    .centerCrop()
                    .error(R.drawable.no_resource_icon)
                    .into(filmBackgroundImageView)

                Glide.with(it)
                    .load("https://image.tmdb.org/t/p/w500" + moviePreview.posterAddress)
                    .override(400, 800)
                    .transform(RoundedCorners(16))
                    .error(R.drawable.no_resource_icon)
                    .into(filmPosterImage)
            }
        }
    }

    private fun configActorRecycler() {
        binding.actorsRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = this@MovieDetailsFragment.adapter
            addItemDecoration(ActorDecorator(20))
        }

        viewModel.fetchActorDetails(moviePreview.id).observe(this,
            { t -> t?.let { adapter.setList(it.cast) } })
    }
}