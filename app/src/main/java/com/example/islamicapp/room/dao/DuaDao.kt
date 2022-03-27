package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.response.local.duaas.Supplication
import kotlinx.coroutines.flow.Flow

@Dao
interface DuaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuas(file: Supplication): Long?

    @Query("SELECT * FROM supplication")
    fun getAllDuas(): Flow<List<Supplication>>

    @Query("SELECT * FROM supplication")
    suspend fun getDuaData(): List<Supplication>?

    @Update
    suspend fun updateDuas(chapter: Supplication): Int

    @Delete
    suspend fun deleteDuas(chapter: Supplication): Int

    @Query("SELECT * FROM supplication ORDER BY RANDOM() LIMIT 1")
    fun getRandomDuas(): Flow<Supplication>
    
    
}