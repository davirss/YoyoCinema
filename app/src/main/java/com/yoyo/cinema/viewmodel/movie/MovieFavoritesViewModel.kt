package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.asLiveData
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MovieFavoritesViewModel(movieRepository: MovieRepository) :
    BaseMovieViewModel(movieRepository) {

    val favoriteMovies = movieRepository.favoreMovies.flowOn(Dispatchers.IO).asLiveData()

}