package com.yoyo.cinema.di.modules

import com.google.gson.GsonBuilder
import com.yoyo.cinema.BuildConfig
import com.yoyo.cinema.model.repository.api.TheMovieDbApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val baseUrl = "https://api.themoviedb.org/3/"

val networkModule =  module {
    factory { ApiKeyInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideTmdbApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

fun provideOkHttpClient(interceptor: ApiKeyInterceptor) =
    OkHttpClient().newBuilder().addInterceptor(interceptor).build()

fun provideTmdbApi(retrofit: Retrofit) = retrofit.create(TheMovieDbApi::class.java)

class ApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithApiKey = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TheMovieDB_APIKEY)
            .build()

        println(urlWithApiKey.url().toString())

        val updatedRequest = chain.request()
            .newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(updatedRequest)
    }
}