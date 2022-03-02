package com.example.islamicapp.util

import androidx.room.TypeConverter
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.hadess_book_response.Book
import com.example.islamicapp.response.local.hadess_book_response.Hadith
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

    @JvmStatic
    @TypeConverter
    fun stringToHadithObjectList(data: String?): List<Hadith> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Hadith>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun hadithToString(someObjects: List<Hadith>?): String {
        return gson.toJson(someObjects)
    }

    @JvmStatic
    @TypeConverter
    fun stringToBookObjectList(data: String?): List<Book> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Book>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun bookToString(someObjects: List<Book>?): String {
        return gson.toJson(someObjects)
    }

}