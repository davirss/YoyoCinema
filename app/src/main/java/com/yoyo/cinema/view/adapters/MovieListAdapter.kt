package com.yoyo.cinema.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoyo.cinema.R
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.viewmodel.MovieSearchViewModel
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    var movieList: List<MovieItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(movieItem: MovieItem) {
        itemView.movieTitle.text = movieItem.originalTitle
    }
}