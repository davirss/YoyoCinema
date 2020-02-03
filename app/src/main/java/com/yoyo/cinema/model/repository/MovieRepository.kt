package com.yoyo.cinema.model.repository

import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.model.repository.db.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieDbApi: TheMovieDbApi, private val movieDao: MovieDao) {

    val favoreMovies: Flow<List<MovieItem>>
        get() = movieDao.getAllFavoriteMovies()

    fun getMovieList(query: String) = flow {
        val apiMovieList = queryMovieListAPI(query)

        // This is extreme ugly. But couldn't think in a better way to do it as of now
        favoreMovies.collect {
            it.forEach { favoriteMovie ->
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

    fun isMovieFavorited(id: Long) = movieDao.isFavorited(id)


    fun updateFavorite(movie: MovieItem) = flow {
        println(movie)
        if (movie.isFavorited) {
            emit(movieDao.addFavoriteMovie(movie))
        } else {
            emit(movieDao.removeFavoriteMovie(movie))
        }
    }

}