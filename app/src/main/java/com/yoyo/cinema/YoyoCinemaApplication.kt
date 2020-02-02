package com.yoyo.cinema

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YoyoCinemaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YoyoCinemaApplication)
        }
    }
}