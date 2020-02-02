package com.yoyo.cinema.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.yoyo.cinema.model.repository.db.entities.Genre
import java.util.*

@Entity(tableName = "movies")
data class MovieItem(
    @SerializedName("poster_path")
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("genre_ids")
    val genreList: List<Int>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @PrimaryKey
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var isFavorited: Boolean = false
)