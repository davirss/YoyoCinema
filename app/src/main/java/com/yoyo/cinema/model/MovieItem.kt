package com.yoyo.cinema.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class MovieItem(
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @PrimaryKey
    val id: Long,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var isFavorited: Boolean = false
)