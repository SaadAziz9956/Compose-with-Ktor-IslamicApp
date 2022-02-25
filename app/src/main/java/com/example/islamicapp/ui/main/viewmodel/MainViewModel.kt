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
    private val context: App
) : ViewModel() {

    private var city: String? = null

    private val currentDate = DateFormat.format("yyyy-MM-dd", Date()).toString()

    private var _prayerTiming = mutableStateOf(PrayerTimingEntity(hijri = ""))
    val prayerTiming: State<PrayerTimingEntity> = _prayerTiming

    private var _nextPrayer = mutableStateOf("Prayer time loading")
    val nextPrayer: State<String> = _nextPrayer

    private var _now = mutableStateOf("Prayer time loading")
    val now: State<String> = _now

    private var _islamicDate = mutableStateOf("Date time loading")
    val islamicDate: State<String> = _islamicDate

    private var _currentDay = mutableStateOf("Today Day")
    val currentDay: State<String> = _currentDay

    private var _city = mutableStateOf("finding city")
    val cityState: State<String> = _city

    private var _intent = mutableStateOf(false)
    val intent: State<Boolean> = _intent


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentLocation()
            sendRequest()
            getTiming()
        }
    }


    private suspend fun getCurrentLocation() {
        Timber.d("getCurrentLocation")

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            withContext(Dispatchers.Main) {
                Timber.d("checkSelfPermission")
                _intent.value = true
            }
        } else {
            Timber.d("GetCity")

            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (location == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Location Not found", Toast.LENGTH_LONG).show()
                }
            } else {
                val geocoder = Geocoder(context)
                try {
                    val user = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    val lat = user[0].latitude
                    val lng = user[0].longitude
                    val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
                    city = addresses[0].locality
                    withContext(Dispatchers.Main) {
                        _city.value = city as String
                    }
                    Timber.d("City Name: $city")
                    Timber.d(" DDD lat: $lat,  longitude: $lng")
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "${e.message} Check internet connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Timber.d("Exception: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getTiming() {

        repo.getFromDB(city).onEach { list ->

            if (!list.isNullOrEmpty()) {

                Timber.d("City : ${list[0].city}")

                list.forEachIndexed { index, prayerTimingEntity ->

                    if (currentDate == prayerTimingEntity.gregorian) {

                        setPrayerTimings(prayerTimingEntity, list[index + 1])

                    }

                }

            }

        }.launchIn(viewModelScope)
    }

    private suspend fun sendRequest() {

        city?.let {

            val dataByCity = repo.getDataByCity(it)

            val dataByDate = repo.getDataByDate(currentDate)

            if (dataByCity.isNullOrEmpty() || dataByDate.isNullOrEmpty()) {
                Timber.d("Request sent")
                repo.prayerTimingRequest(it)
            }
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
    private fun setPrayerTimings(
        prayerTiming: PrayerTimingEntity,
        prayerTimingNextDay: PrayerTimingEntity
    ) {

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
        val asr = "${prayerTiming.gregorian} ${prayerTiming.Asr}"
        val dhuhr = "${prayerTiming.gregorian} ${prayerTiming.Dhuhr}"
        val fajr = "${prayerTiming.gregorian} ${prayerTiming.Fajr}"
        val fajrNextDay = "${prayerTimingNextDay.gregorian} ${prayerTimingNextDay.Fajr}"
        val maghrib = "${prayerTiming.gregorian} ${prayerTiming.Maghrib}"
        val isha = "${prayerTiming.gregorian} ${prayerTiming.Isha}"
        val islamicDate = prayerTiming.hijri
        val midNight = "${prayerTimingNextDay.gregorian} ${prayerTiming.Midnight}"
        val sunRise = "${prayerTiming.gregorian} ${prayerTiming.Sunrise}"

        val tomorrowDate = SimpleDateFormat("yyyy-MM-dd")
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH)

        val tomorrowTime = Calendar.getInstance()
        val newDate = "${prayerTimingNextDay.gregorian} 11:59:59"
        tomorrowTime.time = tomorrowDate.parse(newDate)

        val currentTime = Calendar.getInstance()
        val todayTime = currentTime.timeInMillis

        val fajrTime = Calendar.getInstance()
        fajrTime.time = simpleDateFormat.parse(fajr)

        val dhuhrTime = Calendar.getInstance()
        dhuhrTime.time = simpleDateFormat.parse(dhuhr)

        val asrTime = Calendar.getInstance()
        asrTime.time = simpleDateFormat.parse(asr)

        val maghribTime = Calendar.getInstance()
        maghribTime.time = simpleDateFormat.parse(maghrib)

        val ishaTime = Calendar.getInstance()
        ishaTime.time = simpleDateFormat.parse(isha)

        val sunRiseTime = Calendar.getInstance()
        sunRiseTime.time = simpleDateFormat.parse(sunRise)

        val midNightTime = Calendar.getInstance()
        midNightTime.time = simpleDateFormat.parse(midNight)

        val fajrNextDayTime = Calendar.getInstance()
        fajrNextDayTime.time = simpleDateFormat.parse(fajrNextDay)

        _islamicDate.value =
            IslamicDateConverter.islamicDateFormatChange(islamicDate)

        todayTime.let {
            when {
                todayTime < tomorrowTime.timeInMillis -> {
                    when {
                        todayTime < fajrTime.timeInMillis -> {
                            _nextPrayer.value = "Fajr - ${prayerTiming.Fajr}"
                        }
                        todayTime < dhuhrTime.timeInMillis -> {
                            _nextPrayer.value = "Dhuhr - ${prayerTiming.Dhuhr}"
                        }
                        todayTime < asrTime.timeInMillis -> {
                            _nextPrayer.value = "Asr - ${prayerTiming.Asr}"
                        }
                        todayTime < maghribTime.timeInMillis -> {
                            _nextPrayer.value = "Maghrib - ${prayerTiming.Maghrib}"
                        }
                        todayTime < ishaTime.timeInMillis -> {
                            _nextPrayer.value = "Isha - ${prayerTiming.Isha}"
                        }
                        todayTime < fajrNextDayTime.timeInMillis -> {
                            _nextPrayer.value = "Fajr - ${prayerTimingNextDay.Fajr}"
                        }
                        else -> Unit
                    }
                }
                else -> {
                    if (todayTime < fajrTime.timeInMillis) {
                        _nextPrayer.value = "Fajr - ${prayerTiming.Fajr}"
                    }
                }
            }

        }

        todayTime.let {
            when {
                todayTime > fajrTime.timeInMillis && todayTime < sunRiseTime.timeInMillis -> {
                    Timber.d("Current TIme : $todayTime")
                    _now.value = "Fajr"
                }
                todayTime > dhuhrTime.timeInMillis && todayTime < asrTime.timeInMillis -> {
                    _now.value = "Dhuhr"
                }
                todayTime > asrTime.timeInMillis && todayTime < maghribTime.timeInMillis -> {
                    _now.value = "Asr"
                }
                todayTime > maghribTime.timeInMillis && todayTime < ishaTime.timeInMillis -> {
                    _now.value = "Maghrib"
                }
                todayTime > ishaTime.timeInMillis && todayTime < midNightTime.timeInMillis -> {
                    _now.value = "Isha"
                }
                todayTime > sunRiseTime.timeInMillis && todayTime < dhuhrTime.timeInMillis -> {
                    _now.value = "Sunrise"
                }
                else -> _now.value = "Tahajud"
            }
        }
    }

}

