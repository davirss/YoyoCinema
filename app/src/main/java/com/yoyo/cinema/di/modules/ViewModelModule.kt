package com.yoyo.cinema.di.modules

import com.yoyo.cinema.viewmodel.movie.MovieDetailsViewModel
import com.yoyo.cinema.viewmodel.movie.MovieFavoritesViewModel
import com.yoyo.cinema.viewmodel.movie.MovieSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieSearchViewModel(get()) }
    viewModel { (movieId: Long) ->
        MovieDetailsViewModel(
            get(),
            movieId
        )
    }
    viewModel { MovieFavoritesViewModel(get()) }
}