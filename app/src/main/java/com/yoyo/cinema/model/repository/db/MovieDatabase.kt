package com.yoyo.cinema.model.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yoyo.cinema.model.MovieItem
import com.yoyo.cinema.model.repository.db.dao.MovieDao

@Database(entities = [MovieItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}