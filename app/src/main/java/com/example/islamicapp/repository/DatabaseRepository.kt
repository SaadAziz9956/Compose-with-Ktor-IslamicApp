package com.example.islamicapp.repository

import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.room.dao.Daos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepository(
    private val dao: Daos
) {

    suspend fun insertChapters(chapters: List<Surah>) {
        return withContext(Dispatchers.IO) {
            chapters.onEach { cahpter ->
                dao.chapterDao.insertChapter(cahpter)
            }
        }
    }

    suspend fun insertHadith(books: List<HadeesBookItem>) {
        return withContext(Dispatchers.IO) {
            books.onEach { book ->
                dao.hadithDao.insertHadith(book)
            }
        }
    }

    fun getAllChapters(): Flow<List<Surah>> {
        return dao.chapterDao.getChapters()
    }

    fun getAllHadiths(): Flow<List<HadeesBookItem>> {
        return dao.hadithDao.getAllHadith()
    }

    fun getRandomChapter(): Flow<Surah> {
        return dao.chapterDao.getRandomChapter()
    }

    fun getRandomHadith(): Flow<HadeesBookItem> {
        return dao.hadithDao.getRandomHadith()
    }


    suspend fun getAllChaptersData(): List<Surah>? {
        return withContext(Dispatchers.IO) {
            dao.chapterDao.getData()
        }
    }

    suspend fun getAllHadithData(): List<HadeesBookItem>? {
        return withContext(Dispatchers.IO) {
            dao.hadithDao.getHadithData()
        }
    }

    suspend fun insertPrayerTiming(timing: PrayerTimingEntity): Long? {
        return withContext(Dispatchers.IO) {
            dao.prayerTiming.insertPrayerTiming(timing)
        }
    }

    fun getPrayerTiming(city: String?): Flow<List<PrayerTimingEntity>?> {
        return dao.prayerTiming.getPrayerTiming(city)
    }

    suspend fun getDataByCity(city: String): List<PrayerTimingEntity>? {
        return withContext(Dispatchers.IO) {
            dao.prayerTiming.getDataByCity(city)
        }
    }

    suspend fun getDataByDate(currentDate: String): List<PrayerTimingEntity>? {
        return withContext(Dispatchers.IO) {
            dao.prayerTiming.getDataByDate(currentDate)
        }
    }

    suspend fun getSpecificHaidthVolume(volume: String): HadeesBookItem {
        return withContext(Dispatchers.IO) {
            dao.hadithDao.getSpecificVolume(volume)
        }
    }

}