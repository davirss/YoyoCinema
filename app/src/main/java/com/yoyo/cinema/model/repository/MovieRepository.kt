package com.yoyo.cinema.model.repository

import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.model.repository.db.dao.MovieDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class MovieRepository(private val movieDbApi: TheMovieDbApi, private val movieDao: MovieDao) {

    fun getMovieList(query: String) = flow {
        val apiMovieList = queryMovieListAPI(query)

        movieDao.getAllFavoriteMovies().collect {
            it.forEach {favoriteMovie ->
                println(favoriteMovie.originalTitle)
                apiMovieList.find { apiMovieItem ->
                    apiMovieItem.id == favoriteMovie.id
                }?.isFavorited = favoriteMovie.isFavorited
            }
            emit(apiMovieList)
        }
    }

    private suspend fun queryMovieListAPI(query: String) =
        movieDbApi.queryMovies(query).run {
            this.results.toList()
        }


    fun getMovieDetails(id: Long) = flow {
        emit(getMovieDetailsAPI(id))
    }

    private suspend fun getMovieDetailsAPI(id: Long) =
        movieDbApi.getMovieDetail(id)

    suspend fun updateFavorite(movie: MovieItem) = flow {
        println(movie)
        if (movie.isFavorited) {
            emit(movieDao.addFavoriteMovie(movie))
        } else {
            emit(movieDao.removeFavoriteMovie(movie))
        }
    }

}