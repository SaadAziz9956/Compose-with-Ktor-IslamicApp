package com.example.islamicapp.util

import androidx.room.TypeConverter
import com.example.expensemanagment.response.local.book_response.Ayah
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonTypeConvertor {
    private val gson = Gson()
    @JvmStatic
    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Ayah> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Ayah?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun someObjectListToString(someObjects: List<Ayah?>?): String {
        return gson.toJson(someObjects)
    }
}