package com.yoyo.cinema

import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.model.repository.api.retrofit.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class Playground {

    @Test
    fun test_movie_query_api() {
        runBlocking {
            val movieDbApi: TheMovieDbApi = RetrofitClient().movieDbApi
            assert(movieDbApi.queryMovies("Titanic").results.isNotEmpty())
        }
    }
}
