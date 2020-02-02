package com.yoyo.cinema

import com.yoyo.cinema.di.modules.networkModule
import com.yoyo.cinema.di.modules.repositoryModule
import com.yoyo.cinema.model.repository.MovieRepository
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import com.yoyo.cinema.viewmodel.MovieSearchViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class ApiTest : KoinTest {

    val movieDbApi: TheMovieDbApi by inject()
    val movieSearchViewModel: MovieSearchViewModel by inject()
    val movieRepository: MovieRepository by inject()

    @Test
    fun test_movie_query_api() {
        startKoin {
            modules( networkModule )
            modules( repositoryModule)
        }
        runBlocking {
            val resut = movieRepository.getMovieList("Titanic").collect {

            }
//            assert(resut.results[0].originalTitle.isNotEmpty())
        }
    }
}
