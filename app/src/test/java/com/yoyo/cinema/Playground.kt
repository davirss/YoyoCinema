package com.yoyo.cinema

import com.yoyo.cinema.di.modules.networkModule
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.model.repository.api.retrofit.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class ApiTest : KoinTest {

    val movieDbApi: TheMovieDbApi by inject()

    @Test
    fun test_movie_query_api() {
        startKoin {
            modules( networkModule )
        }
        runBlocking {
            assert(movieDbApi.queryMovies("Titanic").results.isNotEmpty())
        }
    }
}
