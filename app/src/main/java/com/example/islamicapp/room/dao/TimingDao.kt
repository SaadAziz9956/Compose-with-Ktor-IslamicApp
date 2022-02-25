package com.example.islamicapp.room.dao

import androidx.room.*
import com.example.islamicapp.room.entity.PrayerTimingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimingDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerTiming(prayerTiming: PrayerTimingEntity): Long?

    @Query("SELECT * FROM prayertimingentity WHERE city = :city")
    fun getPrayerTiming(city: String?): Flow<List<PrayerTimingEntity>?>

    @Update
    suspend fun updatePrayerTiming(prayerTiming: PrayerTimingEntity): Int

    @Query("DELETE FROM prayertimingentity")
    suspend fun deletePrayerTiming(): Int

    @Query("SELECT * FROM prayertimingentity WHERE city = :city")
    suspend fun getDataByCity(city: String): List<PrayerTimingEntity>?

    @Query("SELECT * FROM prayertimingentity WHERE gregorian = :currentDate")
    fun getDataByDate(currentDate: String): List<PrayerTimingEntity>?


}