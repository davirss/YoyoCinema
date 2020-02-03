package com.yoyo.cinema.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: Long
) : BaseMovieViewModel(movieRepository) {

    private val _details = MutableLiveData<MovieItem>()
    val details: LiveData<MovieItem>
        get() = _details

    private val _isFavorited = MutableLiveData<Boolean>(false)
    val isFavorited: LiveData<Boolean>
        get() = _isFavorited

    fun refreshData() {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId).collect {
                println("Detail collected")
                _details.value = it
            }
            movieRepository.isMovieFavorited(movieId).flowOn(Dispatchers.IO).collect {
                println("Fav collected")
                _isFavorited.value = it ?: false
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            _details.value?.let {
                println("Sei la ${it.isFavorited}")
                it.isFavorited = !_isFavorited.value!!
                movieRepository.updateFavorite(it).collect()
            }
        }
    }
}