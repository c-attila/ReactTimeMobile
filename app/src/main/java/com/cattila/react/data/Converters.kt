package com.cattila.react.data

import androidx.room.TypeConverter
import org.threeten.bp.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return if (value == null) null else LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault())
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneId.systemDefault())?.toEpochSecond()
    }

}