package com.yoyo.cinema.di.modules

import androidx.room.Room
import com.yoyo.cinema.model.repository.MovieRepository
import com.yoyo.cinema.model.repository.db.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get(), get()) }
    single {
        Room.databaseBuilder(androidApplication(), MovieDatabase::class.java, "movie_db")
            .build()
    }
    single {
        get<MovieDatabase>().movieDao()
    }
}
