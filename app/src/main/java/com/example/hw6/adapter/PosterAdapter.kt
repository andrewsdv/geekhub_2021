package com.example.hw6.adapter

import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hw6.R
import com.example.hw6.databinding.PosterItemBinding
import com.example.hw6.helper.getProgressDrawable
import com.example.hw6.model.MoviePreview

interface PosterLoader {
    fun onPosterClicked(moviePreview: MoviePreview)
}

class PosterAdapter(private val listener: PosterLoader)
    : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {
    private var list = listOf<MoviePreview>()
    fun setList(newList: List<MoviePreview>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class PosterViewHolder(private val binding: PosterItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(preview: MoviePreview) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500" + preview.posterAddress)
                    .override(400, 800)
                    .transform(RoundedCorners(16))
                    .into(binding.filmPosterImageView)

                binding.filmPosterImageView.setOnClickListener {
                    listener.onPosterClicked(preview)
                }

                preview.rate?.let {
                    val intRate = (it * 10).toInt()

                    val unwrappedDrawable =
                        AppCompatResources.getDrawable(itemView.context, R.drawable.progress_circle)
                                as LayerDrawable?

                    unwrappedDrawable?.let { drawable -> getProgressDrawable(intRate, drawable) }
                    binding.apply {
                        percentsTextView.text = String.format(itemView.resources.getString(R.string.percents), (intRate.toString()))
                        filmRateProgressBar.progressDrawable = unwrappedDrawable
                        filmRateProgressBar.progress = intRate
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(PosterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}