package com.example.catalogue.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catalogue.R
import com.example.catalogue.entity.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    var items: List<Movie> = emptyList()

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titre: TextView = itemView.findViewById(R.id.titre_movie)
        val description: TextView = itemView.findViewById(R.id.description_movie)
        val image: ImageView = itemView.findViewById(R.id.image_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.titre.text = items[position].titre
        holder.description.text = items[position].description
        Glide.with(holder.itemView).load(items[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}