package com.yoyo.cinema.model.repository

import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.api.TheMovieDbApi

class MovieRepository(private val movieDbApi: TheMovieDbApi) {

    suspend fun queryMovieList(query: String): List<MovieItem> =  movieDbApi.queryMovies(query).results

}