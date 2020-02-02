package com.yoyo.cinema

import android.app.Application
import com.yoyo.cinema.di.modules.networkModule
import com.yoyo.cinema.di.modules.repositoryModule
import com.yoyo.cinema.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class YoyoCinemaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YoyoCinemaApplication)
            loadKoinModules(arrayListOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}