package com.example.islamicapp.ui.main.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.text.format.DateFormat
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.App
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.repository.PrayerTimingRepository
import com.example.islamicapp.response.network.prayer_timing.PrayerTiming
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.util.DataState
import com.example.islamicapp.util.IslamicDateConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repo: PrayerTimingRepository,
    private val dbRepo: DatabaseRepository,
    private val context: App
) : ViewModel() {

    private var city: String? = null

    private var _prayerTiming = mutableStateOf(PrayerTimingEntity(hijri = ""))
    val prayerTiming: State<PrayerTimingEntity> = _prayerTiming

    private var _nextPrayer = mutableStateOf("Prayer time loading")
    val nextPrayer: State<String> = _nextPrayer

    private var _now = mutableStateOf("Prayer time loading")
    val now: State<String> = _now

    private var _islamicDate = mutableStateOf("Date time loading")
    val islamicDate: State<String> = _islamicDate

    private var _currentDate = mutableStateOf("Date time loading")
    val currentDate: State<String> = _currentDate

    private var _currentDay = mutableStateOf("Today Day")
    val currentDay: State<String> = _currentDay

    private var _city = mutableStateOf("finding city")
    val cityState: State<String> = _city

    private var _intent = mutableStateOf(false)
    val intent: State<Boolean> = _intent

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                getCurrentLocation()
            }
            getTiming()
        }
    }


    private fun getCurrentLocation() {
        Timber.d("getCurrentLocation")

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Timber.d("checkSelfPermission")
            _intent.value = true

        } else {
            Timber.d("GetCity")
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (location == null) {
                Toast.makeText(context, "Location Not found", Toast.LENGTH_LONG).show()
            } else {
                val geocoder = Geocoder(context)
                try {
                    val user = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    val lat = user[0].latitude
                    val lng = user[0].longitude
                    val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
                    city = addresses[0].locality
                    _city.value = city as String
                    Timber.d("City Name: $city")
                    Timber.d(" DDD lat: $lat,  longitude: $lng")
                } catch (e: Exception) {
                    Timber.d("Exception: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getTiming() {

        repo.getFromDB().onEach { list ->

            if (!list.isNullOrEmpty()) {

                Timber.d("City : ${list[0].city}")

                if (list[0].city == city) {

                    val currentDate = DateFormat.format("yyyy-MM-dd", Date())

                    _currentDate.value =
                        IslamicDateConverter.dateFormatChange(currentDate.toString())

                    list.forEach { item ->

                        if (currentDate.toString() == item.gregorian) {

                            setPrayerTimings(item)

                        }

                    }
                }

            } else {
                sendRequest()
            }

        }.launchIn(viewModelScope)
    }

    private suspend fun sendRequest() {

        city?.let {
            Timber.d("Request sent")
            repo.prayerTimingRequest(it)
        }

        when (val value = repo.response.value) {

            is DataState.Success<PrayerTiming> -> {
                Timber.d("Success")

                val prayerTiming = value.data.results.datetime

                prayerTiming.forEach { datetime ->

                    val times = datetime.times
                    val date = datetime.date

                    Timber.d("City : $city")

                    val entity = PrayerTimingEntity(
                        Asr = times.Asr,
                        Dhuhr = times.Dhuhr,
                        Fajr = times.Fajr,
                        Imsak = times.Imsak,
                        Isha = times.Isha,
                        Maghrib = times.Maghrib,
                        Midnight = times.Midnight,
                        Sunrise = times.Sunrise,
                        Sunset = times.Sunset,
                        gregorian = date.gregorian,
                        hijri = date.hijri,
                        timestamp = date.timestamp,
                        city = city
                    )

                    repo.insertInDB(entity)
                }

            }

            is DataState.Error -> {
                Timber.d("Exception: ${value.exception}")
            }
            DataState.Idle -> {
                Timber.d("Idle")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setPrayerTimings(prayerTiming: PrayerTimingEntity) {

        val calendar = Calendar.getInstance()

        when (calendar[Calendar.DAY_OF_WEEK]) {

            Calendar.SUNDAY -> {
                _currentDay.value = "Sunday"
            }
            Calendar.MONDAY -> {
                _currentDay.value = "Monday"
            }
            Calendar.TUESDAY -> {
                _currentDay.value = "Tuesday"
            }
            Calendar.WEDNESDAY -> {
                _currentDay.value = "Wednesday"
            }
            Calendar.THURSDAY -> {
                _currentDay.value = "Thursday"
            }
            Calendar.FRIDAY -> {
                _currentDay.value = "Friday"
            }
            Calendar.SATURDAY -> {
                _currentDay.value = "Saturday"
            }
        }

        val asr = prayerTiming.Asr
        val dhuhr = prayerTiming.Dhuhr
        val fajr = prayerTiming.Fajr
        val maghrib = prayerTiming.Maghrib
        val isha = prayerTiming.Isha
        val islamicDate = prayerTiming.hijri
        val midNight = prayerTiming.Midnight
        val sunRise = prayerTiming.Sunrise

        val currentTimee = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("hh:mm a")
        val date = Date(currentTimee)
        val time = simpleDateFormat.format(date)
        val currentTime = simpleDateFormat.parse(time)

        val asrTime = simpleDateFormat.parse(asr)
        val dhuhrTime = simpleDateFormat.parse(dhuhr)
        val fajrTime = simpleDateFormat.parse(fajr)
        val maghribTime = simpleDateFormat.parse(maghrib)
        val ishaTime = simpleDateFormat.parse(isha)
        val midNightTime = simpleDateFormat.parse(midNight)
        val sunriseTime = simpleDateFormat.parse(sunRise)

        _islamicDate.value =
            IslamicDateConverter.islamicDateFormatChange(islamicDate)

        currentTime?.let {
            when {
                currentTime < fajrTime -> {
                    _nextPrayer.value = "Fajr - $fajr"
                }
                currentTime < dhuhrTime -> {
                    _nextPrayer.value = "Dhuhr - $dhuhr"
                }
                currentTime < asrTime -> {
                    _nextPrayer.value = "Asr - $asr"
                }
                currentTime < maghribTime -> {
                    _nextPrayer.value = "Maghrib - $maghrib"
                }
                currentTime < ishaTime -> {
                    _nextPrayer.value = "Isha - $isha"
                }
                else -> Unit
            }
        }

        currentTime?.let {
            when {
                currentTime > fajrTime && currentTime < sunriseTime -> {
                    _now.value = "Fajr"
                }
                currentTime > dhuhrTime && currentTime < asrTime -> {
                    _now.value = "Dhuhr"
                }
                currentTime > asrTime && currentTime < maghribTime -> {
                    _now.value = "Asr"
                }
                currentTime > maghribTime && currentTime < ishaTime -> {
                    _now.value = "Maghrib"
                }
                currentTime > ishaTime && currentTime < midNightTime -> {
                    _now.value = "Isha"
                }
                currentTime > sunriseTime && currentTime < dhuhrTime -> {
                    _now.value = "Sunrise"
                }
                currentTime > midNightTime && currentTime < fajrTime -> {
                    _now.value = "Tahajud"
                }
                else -> _now.value = "Next prayer starting soon"
            }
        }
    }

}

