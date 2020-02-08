package com.quintype.oxygen.entities

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.quintype.oxygen.models.story.Story
import java.util.*
import kotlin.collections.ArrayList

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Story>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}