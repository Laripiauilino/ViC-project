package com.larissa.integrativeproject.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class DataTypeConverter{

    @TypeConverter
    fun listToJsonString(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}