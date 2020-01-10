package com.joaolfp.popularmovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaolfp.popularmovies.R
import com.joaolfp.popularmovies.vo.MoviesVO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(
    private val moviesList: List<MoviesVO>
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.bindItems(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(moviesVO: MoviesVO) {
            itemView.txtTitle.text = moviesVO.title

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + moviesVO.poster_path)
                .into(itemView.poster)
        }
    }
}