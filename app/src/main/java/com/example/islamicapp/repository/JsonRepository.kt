package com.example.islamicapp.repository

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

class JsonRepository(
    private val context: Context,
    private val dbRepo: DatabaseRepository
) {

    private val _intent = mutableStateOf(false)
    val intent: State<Boolean> = _intent

    suspend fun getDataFromJson() {
        if (dbRepo.getAllHadithData()
                .isNullOrEmpty()
        ) {
            Timber.d("getDataFromJson")
            getChapterData()
            getHadithData()
            if (getChapterData() && getHadithData()) {
                _intent.value = true
            } else {
                getDataFromJson()
            }
        } else {
            Timber.d("Intent")
            delay(800)
            _intent.value = true
        }
    }

    private suspend fun getChapterData(): Boolean {
        val jsonFileString: String? = getJsonFromAssets(context, "Quran.JSON")
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<Surah>>() {}.type
        val users: List<Surah> = gson.fromJson(jsonFileString, listUserType)

        dbRepo.insertChapters(users)

        val allChaptersData = dbRepo.getAllChaptersData()
        Timber.d("Chapter inserted")
        return allChaptersData != null
    }

    private suspend fun getHadithData(): Boolean {

        val jsonFileString: String? = getJsonFromAssets(context, "Bukhari.JSON")
        val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
        val token = object : TypeToken<List<HadeesBookItem>>() {}.type
        val hadeesBookItem: List<HadeesBookItem> = gson.fromJson(jsonFileString, token)

        dbRepo.insertHadith(hadeesBookItem)

        val getAllHadithData = dbRepo.getAllHadithData()

        Timber.d("Hadith inserted")
        return getAllHadithData != null
    }

    private fun getJsonFromAssets(context: Context, json: String): String? {
        val jsonString: String = try {
            val `is`: InputStream = context.assets.open(json)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

}