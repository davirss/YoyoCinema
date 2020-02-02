package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.*
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import com.yoyo.cinema.viewmodel.BaseMovieViewModel
import com.yoyo.cinema.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieSearchViewModel(private val movieRepository: MovieRepository) : BaseMovieViewModel(movieRepository) {
    val movieResults = MutableLiveData<List<MovieItem>>()

    fun queryMovies(query: String?) {
        query?.let {
            lastJob?.cancel()
            lastJob = viewModelScope.launch {
                movieRepository.getMovieList(query).collect {
                    movieResults.value = it
                }
            }
        }
    }
}