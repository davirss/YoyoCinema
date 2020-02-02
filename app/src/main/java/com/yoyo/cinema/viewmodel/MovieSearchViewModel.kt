package com.yoyo.cinema.viewmodel

import androidx.lifecycle.*
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieSearchViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    val movieResults = MutableLiveData<List<MovieItem>>()
    val query = MutableLiveData<String>()

    fun queryMovies() {
        lastJob?.cancel()
        lastJob = viewModelScope.launch {
            movieRepository.getMovieList(query.value!!).collect {
                movieResults.value  = it
            }
        }
    }
}