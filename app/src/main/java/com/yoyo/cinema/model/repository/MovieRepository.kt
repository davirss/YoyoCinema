package com.yoyo.cinema.model.repository

import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieDbApi: TheMovieDbApi) {


    fun getMovieList(query: String) = flow {
        emit(queryMovieListAPI(query))
    }

    private suspend fun queryMovieListAPI(query: String) =
        movieDbApi.queryMovies(query).run {
            this.results
        }

    fun getMovieDetails(id: Long) = flow {
        emit(getMovieDetailsAPI(id))
    }

    private suspend fun getMovieDetailsAPI(id: Long) =
        movieDbApi.getMovieDetail(id)

}