package com.example.islamicapp.repository

import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.room.dao.Daos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepository(
    private val dao: Daos
) {

    suspend fun insertChapters(chapters: List<Surah>) {
        withContext(Dispatchers.IO) {
            chapters.forEach { chapter ->
                dao.chapterDao.insertChapter(chapter)
            }
        }
    }

    fun getAllChapters(): Flow<List<Surah>> {
        return dao.chapterDao.getChapters()
    }

    suspend fun getAllData(): List<Surah> {
        return withContext(Dispatchers.IO) {
            dao.chapterDao.getData()
        }
    }

    suspend fun updateSurah(chapter: Surah): Int {
        return dao.chapterDao.updateChapter(chapter)
    }

    fun getSpecificVerse(number: Int): Flow<Surah> {
        return dao.chapterDao.getSpecificChapter(number)
    }

    suspend fun insertPrayerTiming(timing: PrayerTimingEntity): Long? {
        return withContext(Dispatchers.IO) {
            dao.prayerTiming.insertPrayerTiming(timing)
        }
    }

    fun getPrayerTiming(): Flow<List<PrayerTimingEntity>?> {
        return dao.prayerTiming.getPrayerTiming()
    }

    suspend fun deletePrayerTiming(): Int {
        return withContext(Dispatchers.IO) {
            dao.prayerTiming.deletePrayerTiming()
        }
    }
}