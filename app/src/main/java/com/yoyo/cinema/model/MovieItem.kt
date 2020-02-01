package com.yoyo.cinema.model

import java.util.*

data class MovieItem(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: Date,
    val genreList: List<Int>,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Int
)