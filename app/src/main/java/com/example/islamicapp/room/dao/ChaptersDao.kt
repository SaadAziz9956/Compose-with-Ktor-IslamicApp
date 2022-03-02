package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.response.local.book_response.Surah
import kotlinx.coroutines.flow.Flow

@Dao
interface ChaptersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(file: Surah): Long?

    @Query("SELECT * FROM surah")
    fun getChapters(): Flow<List<Surah>>

    @Query("SELECT * FROM surah")
    suspend fun getData(): List<Surah>?

    @Update
    suspend fun updateChapter(chapter: Surah): Int

    @Delete
    suspend fun deleteChapter(chapter: Surah): Int

    @Query("SELECT * FROM surah WHERE number = :chapNumber")
    fun getSpecificChapter(chapNumber: Int): Flow<Surah>

    @Query("SELECT * FROM surah ORDER BY RANDOM() LIMIT 1")
    fun getRandomChapter(): Flow<Surah>

    // ORDER BY RANDOM() LIMIT 1

}