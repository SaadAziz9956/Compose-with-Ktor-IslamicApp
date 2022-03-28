package com.example.islamicapp.repository

import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.response.local.duaas.Supplication
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.response.local.names.NamesData
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.room.dao.Daos
import com.example.islamicapp.room.dao.NamesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepository(
    private val dao: Daos
) {

    suspend fun insertChapters(chapters: List<Surah>) {
        return withContext(Dispatchers.IO) {
            chapters.onEach { chapter ->
                dao.chapterDao.insertChapter(chapter)
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

    suspend fun insertNames(names: List<NamesData>) {
        return withContext(Dispatchers.IO) {
            names.onEach { name ->
                dao.namesDao.insertNames(name)
            }
        }
    }

    suspend fun insertDuas(supplications: List<Supplication>) {
        return withContext(Dispatchers.IO) {
            supplications.onEach { supplication ->
                dao.duaDao.insertDuas(supplication)
            }
        }    }

    fun getAllChapters(): Flow<List<Surah>> {
        return dao.chapterDao.getChapters()
    }

    fun getAllDuas(): Flow<List<Supplication>> {
        return dao.duaDao.getAllDuas()
    }

    fun getAllHadiths(): Flow<List<HadeesBookItem>> {
        return dao.hadithDao.getAllHadith()
    }

    fun getRandomChapter(): Flow<Surah> {
        return dao.chapterDao.getRandomChapter()
    }

    fun getRandomName(): Flow<NamesData> {
        return dao.namesDao.getRandomName()
    }

    fun getRandomHadith(): Flow<HadeesBookItem?> {
        return dao.hadithDao.getRandomHadith()
    }

    fun getRandomDua(): Flow<Supplication?> {
        return dao.duaDao.getRandomDuas()
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

    suspend fun getAllNamesData(): List<NamesData>? {
        return withContext(Dispatchers.IO) {
            dao.namesDao.getNamesData()
        }
    }

    suspend fun getAllDuaData(): List<Supplication>? {
        return withContext(Dispatchers.IO) {
            dao.duaDao.getDuaData()
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