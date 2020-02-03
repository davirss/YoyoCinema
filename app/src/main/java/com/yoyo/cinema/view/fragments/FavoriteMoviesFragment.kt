package com.yoyo.cinema.view.fragments

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yoyo.cinema.R
import com.yoyo.cinema.databinding.FragmentMovieFavoriteListBinding
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.view.adapters.MovieListAdapter
import com.yoyo.cinema.viewmodel.movie.MovieFavoritesViewModel
import kotlinx.android.synthetic.main.fragment_movie_favorite_list.*
import kotlinx.android.synthetic.main.fragment_movie_favorite_list.movieListRecyclerview
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteMoviesFragment : BaseFragment(), MovieListAdapter.OnItemFavoriteListener {

    private lateinit var binding: FragmentMovieFavoriteListBinding
    private val movieListAdapter = MovieListAdapter(this)
    private val viewModel: MovieFavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_favorite_list, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener {
            onSearchClick(it)
        }
        movieListRecyclerview.adapter = movieListAdapter
        movieListRecyclerview.layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onFavorite(movieItem: MovieItem) {
        viewModel.toggleFavorite(movieItem)
    }

    override fun setupObservers() {
        viewModel.favoriteMovies.observe(this, Observer {
            movieListAdapter.movieList = it //Possible improvement: Having a "Undo" snackbar
        })
        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                viewModel.errorMessageDisplayed()
            }
        })
    }

    private fun onSearchClick(view: View) {
        findNavController().navigate(R.id.action_favoriteMoviesFragment_to_movieListFragment)
    }



}
