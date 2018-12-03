package com.group15.fitnesstracker.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time
}