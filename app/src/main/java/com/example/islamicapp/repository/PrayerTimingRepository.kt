package com.example.islamicapp.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.islamicapp.response.network.KtorInterface
import com.example.islamicapp.response.network.prayer_timing.PrayerTiming
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.util.DataState
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class PrayerTimingRepository(
    private val client: KtorInterface,
    private val dbRepo: DatabaseRepository
) {

    private val _response = mutableStateOf<DataState<PrayerTiming>>(DataState.Idle)
    val response: State<DataState<PrayerTiming>> = _response

    suspend fun prayerTimingRequest(chapNo: String) {
        try {
            val getPrayerTiming = client.getPrayerTiming(chapNo)
            _response.value = DataState.Success(getPrayerTiming)
        } catch (e: Exception) {
            Timber.d("Exception: $e")
            _response.value = DataState.Error(e)
        } catch (e: RedirectResponseException) {
            Timber.d("Exception: $e")
            _response.value = DataState.Error(e)
        } catch (e: ClientRequestException) {
            Timber.d("Exception: $e")
            _response.value = DataState.Error(e)
        } catch (e: ServerResponseException) {
            Timber.d("Exception: $e")
            _response.value = DataState.Error(e)
        }
    }

    suspend fun insertInDB(entity: PrayerTimingEntity) {
        val insertPrayerTiming = dbRepo.insertPrayerTiming(entity)
        Timber.d("Inserted in db : $insertPrayerTiming")
    }

    fun getFromDB(city: String?): Flow<List<PrayerTimingEntity>?> {
        return dbRepo.getPrayerTiming(city)
    }

}