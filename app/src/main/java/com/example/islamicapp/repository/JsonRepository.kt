package com.example.islamicapp.repository

import android.content.Context
import com.example.expensemanagment.response.local.book_response.Surah
import com.google.gson.Gson
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

    suspend fun getData(): Boolean {
        val data = dbRepo.getAllData()
        return if (data.isEmpty()) {
            Timber.d("getDataFromJson")
            val dataFromJson = getDataFromJson()
            dataFromJson
        } else {
            Timber.d("intentLauncher")
            delay(300)
            true
        }
    }

    private suspend fun getDataFromJson(): Boolean {

        val jsonFileString: String? = getJsonFromAssets(context)
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<Surah>>() {}.type
        val users: List<Surah> = gson.fromJson(jsonFileString, listUserType)

        dbRepo.insertChapters(users)

        return true
    }

    private fun getJsonFromAssets(context: Context): String? {
        val jsonString: String = try {
            val `is`: InputStream = context.assets.open("Quran.JSON")
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