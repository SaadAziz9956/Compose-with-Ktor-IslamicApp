package com.example.islamicapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.room.dao.ChaptersDao
import com.example.islamicapp.room.dao.TimingDao
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.util.GsonTypeConvertor


@Database(
    entities = [
        Surah::class,
        PrayerTimingEntity::class
    ], version = 1,
    exportSchema = false
)

@TypeConverters(
    GsonTypeConvertor::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun chapterDao(): ChaptersDao
    abstract fun prayerTiming(): TimingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "Quran_Database"

        fun getDbInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME,
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }

}