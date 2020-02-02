package com.yoyo.cinema.viewmodel

import androidx.lifecycle.*
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieSearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieResults = MutableLiveData<List<MovieItem>>()
    private var lastJob: Job? = null

    fun queryMovies(query: String) {
        lastJob?.cancel()
        lastJob = viewModelScope.launch {
            movieRepository.getMovieList(query).collect {
                movieResults.value  = it
            }
        }
    }
}