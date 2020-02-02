package com.yoyo.cinema.model.repository.api

import com.google.gson.annotations.SerializedName
import com.yoyo.cinema.model.MovieItem
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

data class BaseApiResponse<ITEM>(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<ITEM>
)

interface TheMovieDbApi {

    @GET("search/movie")
    suspend fun queryMovies(@Query(value = "query") query: String): BaseApiResponse<MovieItem>

}