package com.yoyo.cinema.model.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yoyo.cinema.model.MovieItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movieItem: MovieItem)

    @Delete
    fun removeFavoriteMovie(movieItem: MovieItem)

    @Query("SELECT isFavorited FROM movies WHERE id  = :movieId")
    fun isFavorited(movieId: Long): Flow<Boolean?>

    @Query("SELECT * FROM movies")
    fun getAllFavoriteMovies(): Flow<List<MovieItem>>


}