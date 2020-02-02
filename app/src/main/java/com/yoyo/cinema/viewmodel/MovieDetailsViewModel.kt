package com.yoyo.cinema.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository): BaseViewModel() {

    val details = MutableLiveData<MovieItem>()

    fun loadMovieDetails(id: Long) {
        lastJob?.cancel()
        lastJob = viewModelScope.launch {
            movieRepository.getMovieDetails(id).collect {
                details.value = it
            }
        }
    }
}