package com.yoyo.cinema.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository,
                            private val movieId: Long): BaseViewModel() {

    val details = MutableLiveData<MovieItem>()

    fun loadMovieDetails() {
        lastJob?.cancel()
        lastJob = viewModelScope.launch {
            movieRepository.getMovieDetails(movieId).collect {
                details.value = it
            }
        }
    }
}