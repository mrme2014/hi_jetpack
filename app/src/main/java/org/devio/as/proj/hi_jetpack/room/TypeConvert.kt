package org.devio.`as`.proj.hi_jetpack.room

import androidx.room.TypeConverter
import java.util.*


class DateConvert {

    @TypeConverter
    fun date2Long(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun long2Date(timestamp: Long): Date {
        return Date(timestamp)
    }
}