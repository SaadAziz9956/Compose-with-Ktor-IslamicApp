package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.room.entity.PrayerTimingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimingDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerTiming(prayerTiming: PrayerTimingEntity): Long?

    @Query("SELECT * FROM prayertimingentity")
    fun getPrayerTiming(): Flow<List<PrayerTimingEntity>?>

    @Update
    suspend fun updatePrayerTiming(prayerTiming: PrayerTimingEntity): Int

    @Query("DELETE FROM prayertimingentity")
    suspend fun deletePrayerTiming(): Int

    
}