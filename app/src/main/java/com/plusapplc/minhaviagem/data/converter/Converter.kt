package com.plusapplc.minhaviagem.data.converter

import androidx.room.TypeConverter
import com.plusapplc.minhaviagem.data.entity.Status
import java.util.Date



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
    fun fromStatus(value: String?): Status? {
        return value?.let { enumValueOf<Status>(it) }
    }

    @TypeConverter
    fun statusToString(status: Status?): String? {
        return status?.name
    }
}
