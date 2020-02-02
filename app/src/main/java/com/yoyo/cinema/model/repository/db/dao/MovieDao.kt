package com.yoyo.cinema.model.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yoyo.cinema.model.MovieItem

@Dao
interface MovieDao {

    @Insert
    fun addFavoriteMovie(movieItem: MovieItem)

    @Query("SELECT isFavorited FROM movies where :movieId")
    fun isMovieFavorited(movieId: Long): LiveData<Boolean>


}