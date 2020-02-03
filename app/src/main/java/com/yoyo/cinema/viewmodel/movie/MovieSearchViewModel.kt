package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.*
import com.yoyo.cinema.R
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieSearchViewModel(private val movieRepository: MovieRepository) :
    BaseMovieViewModel(movieRepository) {
    val movieResults = movieRepository.favoreMovies.asLiveData()


    private val _isSearching = MutableLiveData<Boolean>(false)
    val isSearching: LiveData<Boolean>
        get() = _isSearching

    fun queryMovies(query: String?) {
        if (query.isNullOrBlank()) {
            _errorMessage.value = R.string.err_blank_query
            return
        }

        lastJob?.cancel()
        lastJob = viewModelScope.launch {
            try {
                _isLoading.value = true
                movieRepository.getMovieList(query)
                    .collect()
            } catch (e: Throwable) {
                _errorMessage.value = R.string.err_fetch_movie
            } finally {
                _isLoading.value = false
            }
        }
    }
}