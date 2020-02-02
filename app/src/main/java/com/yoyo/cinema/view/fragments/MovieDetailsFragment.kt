package com.yoyo.cinema.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.yoyo.cinema.R
import com.yoyo.cinema.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment() {

    val args: MovieDetailsFragmentArgs by navArgs()
    val viewModel: MovieDetailsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovieDetails(args.movieId)
    }

    override fun setupObservers() {
        viewModel.details.observe(this, Observer {
            movieTitle.text = it.originalTitle
        })
    }
}