package com.example.hw6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hw6.R
import com.example.hw6.databinding.ActorItemBinding
import com.example.hw6.model.Actor


class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {
    private var list = listOf<Actor>()

    fun setList(newList: List<Actor>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class ActorViewHolder(private val binding: ActorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500" + actor.photoAddress)
                    .override(400, 800)
                    .transform(RoundedCorners(16))
                    .error(R.drawable.no_resource_icon)
                    .into(binding.actorImageView)

            with(actor) {
                binding.actorNameTextView.text = name
                binding.actorRoleTextView.text = character
                binding.departmentTextView.text = department
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ActorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
