package com.yoyo.cinema.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoyo.cinema.R
import com.yoyo.cinema.databinding.FragmentMovieListBinding
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.view.adapters.MovieListAdapter
import com.yoyo.cinema.viewmodel.movie.MovieSearchViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : BaseFragment(), MovieListAdapter.OnItemFavoriteListener {

    private val movieModel by viewModel<MovieSearchViewModel>()
    private val movieListAdapter = MovieListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentMovieListBinding>(inflater, R.layout.fragment_movie_list, container, false)
        dataBinding.viewModel = movieModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListRecyclerview.adapter = movieListAdapter
        movieListRecyclerview.layoutManager =  LinearLayoutManager(context)
    }

    override fun onFavorite(movie: MovieItem) {
        movieModel.toggleFavorite(movie)
    }

    override fun setupObservers() {
        movieModel.movieResults.observe(this, Observer {
            Log.d("Ob", "Lista atualizada ${it.size}")
            movieListAdapter.movieList = it
        })
    }


}
