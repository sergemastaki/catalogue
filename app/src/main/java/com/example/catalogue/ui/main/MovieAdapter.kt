package com.example.catalogue.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catalogue.R
import com.example.catalogue.entity.Movie

class MovieAdapter: ListAdapter<Movie,MovieAdapter.MovieHolder>(MovieDiffCallback) {
    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val titre: TextView = itemView.findViewById(R.id.titre_movie)
        private val description: TextView = itemView.findViewById(R.id.description_movie)
        private val image: ImageView = itemView.findViewById(R.id.image_movie)

        fun bind(movie: Movie){
            titre.text = movie.titre
            description.text = movie.description
            Glide.with(itemView).load(movie.image).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}