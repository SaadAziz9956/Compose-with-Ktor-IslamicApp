package com.example.islamicapp.util

import androidx.room.TypeConverter
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.duaas.Dua
import com.example.islamicapp.response.local.duaas.DuaaData
import com.example.islamicapp.response.local.duaas.Supplication
import com.example.islamicapp.response.local.hadess_book_response.Book
import com.example.islamicapp.response.local.hadess_book_response.Hadith
import com.example.islamicapp.response.local.names.Meaning
import com.example.islamicapp.response.local.names.NamesData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

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

    @TypeConverter
    fun stringToNamesObjectList(data: String?): List<NamesData> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<NamesData>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun namesToString(someObjects: List<NamesData>?): String {
        val listType = object : TypeToken<List<NamesData>?>() {}.type
        return gson.toJson(someObjects, listType)
    }

    @TypeConverter
    fun stringToMeaning(data: String?): Meaning? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Meaning>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun meaningToString(someObjects: Meaning): String {
        val listType = object : TypeToken<Meaning>() {}.type
        return gson.toJson(someObjects, listType)
    }

    @TypeConverter
    fun stringToSupplication(data: String?): Supplication? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Supplication>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun supplicationToString(someObjects: Supplication): String {
        val listType = object : TypeToken<Supplication>() {}.type
        return gson.toJson(someObjects, listType)
    }

    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<Dua> {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Dua>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Dua>): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun stringToDuaData(data: String?): DuaaData? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<DuaaData>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun duaDataToString(someObjects: DuaaData): String {
        val listType = object : TypeToken<DuaaData>() {}.type
        return gson.toJson(someObjects, listType)
    }

}