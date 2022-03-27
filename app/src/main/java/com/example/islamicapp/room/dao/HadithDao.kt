package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HadithDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHadith(file: HadeesBookItem): Long?

    @Query("SELECT * FROM hadeesbookitem")
    fun getAllHadith(): Flow<List<HadeesBookItem>>

    @Query("SELECT * FROM hadeesbookitem")
    suspend fun getHadithData(): List<HadeesBookItem>?

    @Update
    suspend fun updateHadith(chapter: HadeesBookItem): Int

    @Delete
    suspend fun deleteHadith(chapter: HadeesBookItem): Int

    @Query("SELECT * FROM hadeesbookitem ORDER BY RANDOM() LIMIT 1")
    fun getRandomHadith(): Flow<HadeesBookItem?>

    @Query("SELECT * FROM hadeesbookitem WHERE name = :volume")
    fun getSpecificVolume(volume: String): HadeesBookItem

}