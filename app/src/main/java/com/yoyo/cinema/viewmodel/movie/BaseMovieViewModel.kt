package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.viewModelScope
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import com.yoyo.cinema.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseMovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    fun toggleFavorite(movieItem: MovieItem) {
        movieItem.isFavorited = !movieItem.isFavorited
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.updateFavorite(movieItem).collect()
        }
    }
}