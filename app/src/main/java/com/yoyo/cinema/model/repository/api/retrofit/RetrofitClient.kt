package com.yoyo.cinema.model.repository.api.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import com.yoyo.cinema.BuildConfig
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val baseUrl = "https://api.themoviedb.org/3/"

    private val apiAuthInterceptor = Interceptor {
        val urlWithApiKey = it.request().url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TheMovieDB_APIKEY)
            .build()

        println(urlWithApiKey.url().toString())

        val updatedRequest = it.request()
            .newBuilder()
            .url(urlWithApiKey)
            .build()

        it.proceed(updatedRequest)
    }

    private val customClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(apiAuthInterceptor)
        .build()

    val movieDbApi: TheMovieDbApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(customClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(TheMovieDbApi::class.java)
    }
}