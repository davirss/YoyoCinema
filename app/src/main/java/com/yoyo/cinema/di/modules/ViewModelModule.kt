package com.yoyo.cinema.di.modules

import com.yoyo.cinema.viewmodel.MovieDetailsViewModel
import com.yoyo.cinema.viewmodel.MovieSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieSearchViewModel(get()) }
    viewModel { (movieId: Long) -> MovieDetailsViewModel(get(), movieId) }
}