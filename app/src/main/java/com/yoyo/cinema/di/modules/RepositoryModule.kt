package com.yoyo.cinema.di.modules

import com.yoyo.cinema.model.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MovieRepository(get()) }
}