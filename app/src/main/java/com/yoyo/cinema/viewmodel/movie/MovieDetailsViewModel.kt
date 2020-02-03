package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.*
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieDetailsViewModel(private val movieRepository: MovieRepository,
                            private val movieId: Long): BaseMovieViewModel(movieRepository) {

    val details: LiveData<MovieItem>
        get() = movieRepository.getMovieDetails(movieId).asLiveData()
    val isFavorited: LiveData<Boolean?>
        get() = movieRepository.isMovieFavorited(movieId).asLiveData(Dispatchers.IO)
}