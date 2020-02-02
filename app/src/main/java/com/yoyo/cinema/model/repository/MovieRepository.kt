package com.yoyo.cinema.model.repository

import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.model.repository.db.dao.MovieDao
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieDbApi: TheMovieDbApi, private val movieDao: MovieDao) {


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