package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.response.local.names.NamesData
import kotlinx.coroutines.flow.Flow

@Dao
interface NamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNames(file: NamesData): Long?

    @Query("SELECT * FROM namesdata")
    fun getAllNames(): Flow<List<NamesData>>

    @Query("SELECT * FROM namesdata")
    suspend fun getNamesData(): List<NamesData>?

    @Update
    suspend fun updateNames(chapter: NamesData): Int

    @Delete
    suspend fun deleteNames(chapter: NamesData): Int

    @Query("SELECT * FROM namesdata ORDER BY RANDOM() LIMIT 1")
    fun getRandomName(): Flow<NamesData>

}