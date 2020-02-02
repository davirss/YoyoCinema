package com.yoyo.cinema.model.repository.api

import com.google.gson.annotations.SerializedName
import com.yoyo.cinema.model.MovieItem
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class BaseApiListResponse<ITEM>(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<ITEM>
)

interface TheMovieDbApi {

    @GET("search/movie")
    suspend fun queryMovies(@Query(value = "query") query: String): BaseApiListResponse<MovieItem>

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path(value = "id") id: Long): MovieItem

}