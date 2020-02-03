package com.yoyo.cinema.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yoyo.cinema.R
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.view.fragments.MovieListFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private val onItemFavoriteListener: OnItemFavoriteListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var movieList: List<MovieItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onItemFavoriteListener)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    interface OnItemFavoriteListener {

        fun onFavorite(movieItem: MovieItem)
    }

    class MovieViewHolder(itemView: View, favorite: OnItemFavoriteListener) :
        RecyclerView.ViewHolder(itemView) {

        lateinit var movieItem: MovieItem

        init {
            itemView.setOnClickListener {
                val action =
                    MovieListFragmentDirections.actionMovieListFragmentToDetails(movieItem.id)
                it.findNavController().navigate(action)
            }
            itemView.favoriteButton.setOnClickListener {
                favorite.onFavorite(movieItem)
            }
        }

        fun bind(movieItem: MovieItem) {
            this.movieItem = movieItem
            itemView.movieTitle.text = movieItem.originalTitle
            itemView.favoriteButton.isChecked = movieItem.isFavorited
        }
    }
}
