package com.yoyo.cinema.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.yoyo.cinema.databinding.FragmentMovieDetailBinding
import com.yoyo.cinema.view.PosterGlideTarget
import com.yoyo.cinema.viewmodel.movie.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModel{
        parametersOf(args.movieId)
    }

    private val constraintSet = ConstraintSet()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshData()
    }

    override fun setupObservers() {
        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                viewModel.errorMessageDisplayed()
            }
        })
        viewModel.details.observe(this, Observer {
            Glide
                .with(this)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .into(PosterGlideTarget(detailsConstraintLayout, posterImageView))
        })
    }
}

@BindingAdapter("app:year")
fun TextView.setTextYearFromDate(date: Date?) {
    date?.let {
        val calendar = Calendar.getInstance()
        calendar.time = date
        this.text = calendar.get(Calendar.YEAR).toString()
    }
}

