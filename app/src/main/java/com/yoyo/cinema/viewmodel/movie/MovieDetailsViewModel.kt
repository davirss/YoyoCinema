package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import com.yoyo.cinema.model.repository.db.dao.MovieDao
import com.yoyo.cinema.viewmodel.BaseMovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository,
                            private val movieDao: MovieDao,
                            private val movieId: Long): BaseMovieViewModel(movieRepository) {

    val details = MutableLiveData<MovieItem>()
    val isFavorited = MutableLiveData<Boolean>()

    fun loadMovieDetails() {
        lastJob?.cancel()
        lastJob = viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovieDetails(movieId).collect {
                details.value = it
            }
            movieDao.isFavorited(movieId).collect {
                isFavorited.value = it
            }
        }
    }
}