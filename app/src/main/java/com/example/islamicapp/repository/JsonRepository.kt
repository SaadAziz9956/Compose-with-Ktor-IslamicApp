package com.example.islamicapp.repository

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.islamicapp.response.local.book_response.BookResponse
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.response.local.duaas.Supplication
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.response.local.names.NamesData
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

        val allChaptersData = dbRepo.getAllChaptersData()
        val allHadithData = dbRepo.getAllHadithData()
        val allNamesData = dbRepo.getAllNamesData()
        val allDuaData = dbRepo.getAllDuaData()

        if (allChaptersData
                .isNullOrEmpty() ||
            allHadithData
                .isNullOrEmpty() ||
            allNamesData
                .isNullOrEmpty() ||
            allDuaData
                .isNullOrEmpty()
        ) {

            Timber.d("getDataFromJson")

            val chapterData = getChapterData()
            val hadithData = getHadithData()
            val namesData = getNamesData()
            val duaData = getDuaData()

            if (chapterData && hadithData && namesData) {
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

    private suspend fun getDuaData(): Boolean {
        val jsonFileString: String? = getJsonFromAssets(context, "Duaas.JSON")
        val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
        val token = object : TypeToken<List<Supplication>>() {}.type
        val supplication: List<Supplication> = gson.fromJson(jsonFileString, token)

        dbRepo.insertDuas(supplication)

        val getAllHadithData = dbRepo.getAllDuaData()

        Timber.d("Dua inserted")

        return getAllHadithData != null
    }

    private suspend fun getChapterData(): Boolean {
        val jsonFileString: String? = getJsonFromAssets(context, "Quran.JSON")
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<Surah>>() {}.type
        val chapters: List<Surah> = gson.fromJson(jsonFileString, listUserType)

        getChapterDataEnglish(chapters)

        val allChaptersData = dbRepo.getAllChaptersData()
        Timber.d("Chapter inserted")
        return allChaptersData != null
    }

    private suspend fun getChapterDataEnglish(chapters: List<Surah>): Boolean {
        val jsonFileString: String? = getJsonFromAssets(context, "QuranEnglish.JSON")
        val gson = Gson()
        val listUserType: Type = object : TypeToken<BookResponse>() {}.type
        val chaptersEnglish: BookResponse = gson.fromJson(jsonFileString, listUserType)

        chaptersEnglish.data.surahs.onEach { translatedChapter ->

            translatedChapter.ayahs.onEach { translatedAyah ->

                chapters.onEach { chapter ->

                    chapter.ayahs.onEach { ayah ->

                        if (translatedAyah.number == ayah.number) {

                            ayah.englishTrans = translatedAyah.text

                        }

                    }

                }

            }

        }

        dbRepo.insertChapters(chapters)

        val allChaptersData = dbRepo.getAllChaptersData()
        Timber.d("English Chapter inserted")
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

    private suspend fun getNamesData(): Boolean {

        val jsonFileString: String? = getJsonFromAssets(context, "AllahNames.JSON")
        val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
        val token = object : TypeToken<List<NamesData>>() {}.type
        val names: List<NamesData> = gson.fromJson(jsonFileString, token)

        dbRepo.insertNames(names)

        val getNamesData = dbRepo.getAllNamesData()

        Timber.d("Names inserted")
        return getNamesData != null
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