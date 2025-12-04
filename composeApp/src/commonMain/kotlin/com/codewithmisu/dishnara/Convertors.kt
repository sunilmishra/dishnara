package com.codewithmisu.dishnara

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Json.decodeFromString(value)
    }
}