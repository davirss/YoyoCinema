package com.yoyo.cinema.model.repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yoyo.cinema.model.repository.db.entities.Genre
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromGenreListToJson(list: List<Genre>?): String? {
        return list.let { Gson().toJson(list) }
    }

    @TypeConverter
    fun fromStringtoGenreList(string: String?): List<Genre>? {
        return string?.let { Gson().fromJson(string) }
    }
}

inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)
