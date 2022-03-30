package com.example.islamicapp.ui.home.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.text.format.DateFormat
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.App
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.repository.PrayerTimingRepository
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.duaas.DuaaData
import com.example.islamicapp.response.local.hadess_book_response.Hadith
import com.example.islamicapp.response.local.names.NamesData
import com.example.islamicapp.response.network.prayer_timing.PrayerTiming
import com.example.islamicapp.room.entity.PrayerTimingEntity
import com.example.islamicapp.util.DataState
import com.example.islamicapp.util.IslamicDateConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

sealed interface MainUiState {

    val isLoading: Boolean
    val errorMessages: String

    /**
     * There are no posts to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoData(
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : MainUiState

    /**
     * There are posts to render, as contained in [postsFeed].
     *
     * There is guaranteed to be a [selectedPost], which is one of the posts from [postsFeed].
     */
    data class HasData(
        val prayerTiming: PrayerTimingEntity? = null,
        val nextPrayer: String? = null,
        val now: String? = null,
        val islamicDate: String? = null,
        val currentDay: String? = null,
        val city: String? = null,
        val verse: Ayah? = null,
        val intent: Boolean? = null,
        val hadith: Hadith? = null,
        val chapterName: String? = null,
        val name: NamesData? = null,
        val chapterNum: Int? = null,
        val randDua: DuaaData? = null,
        val duaType: String? = null,
        val duaName: String? = null,
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : MainUiState
}

private data class MainViewModelState(
    val prayerTiming: PrayerTimingEntity? = null,
    val nextPrayer: String? = null,
    val now: String? = null,
    val islamicDate: String? = null,
    val currentDay: String? = null,
    val city: String? = null,
    val verse: Ayah? = null,
    val intent: Boolean? = null,
    val hadith: Hadith? = null,
    val chapterName: String? = null,
    val name: NamesData? = null,
    val chapterNum: Int? = null,
    val randDua: DuaaData? = null,
    val duaType: String? = null,
    val duaName: String? = null,
    val isLoading: Boolean = false,
    val errorMessages: String = "",
) {

    /*
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): MainUiState =
        MainUiState.HasData(
            prayerTiming = prayerTiming,
            nextPrayer = nextPrayer,
            now = now,
            islamicDate = islamicDate,
            currentDay = currentDay,
            city = city,
            verse = verse,
            intent = intent,
            hadith = hadith,
            chapterName = chapterName,
            name = name,
            chapterNum = chapterNum,
            randDua = randDua,
            duaName = duaName,
            duaType = duaType,
            isLoading = isLoading,
            errorMessages = errorMessages
        )

}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repo: PrayerTimingRepository,
    private val dbRepo: DatabaseRepository,
    private val context: App
) : ViewModel() {

    private val calendar = Calendar.getInstance(TimeZone.getDefault())

    private var city: String? = null

    private var long: Double? = null

    private var lat: Double? = null

    private val currentDate = DateFormat.format("dd-MM-yyyy", Date()).toString()

    private val viewModelState = MutableStateFlow(MainViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

//    private var _prayerTiming = mutableStateOf(PrayerTimingEntity(hijri = ""))
//    val prayerTiming: State<PrayerTimingEntity> = _prayerTiming
//
//    private var _nextPrayer = mutableStateOf("")
//    val nextPrayer: State<String> = _nextPrayer
//
//    private var _now = mutableStateOf("")
//    val now: State<String> = _now

//    private var _islamicDate = mutableStateOf("Date time loading")
//    val islamicDate: State<String> = _islamicDate

//    private var _currentDay = mutableStateOf("Today Day")
//    val currentDay: State<String> = _currentDay
//
//    private var _city = mutableStateOf("finding city")
//    val cityState: State<String> = _city

//    private var _intent = mutableStateOf(false)
//    val intent: State<Boolean> = _intent
//
//    private var _verse = mutableStateOf(Ayah())
//    val verse: State<Ayah> = _verse
//
//    private var _hadith = mutableStateOf(Hadith("", "", ""))
//    val hadith: State<Hadith> = _hadith
//
//    private var _chapterName = mutableStateOf("")
//    val chapterName: State<String> = _chapterName
//
//    private var _name = mutableStateOf(NamesData())
//    val name: State<NamesData> = _name
//
//    private var _chapterNum = mutableStateOf(0)
//    val chapterNum: State<Int> = _chapterNum
//
//    private var _randDua = mutableStateOf(DuaaData("","","",""))
//    val randDua: State<DuaaData> = _randDua
//
//    private var _duaType = mutableStateOf("")
//    val duaType: State<String> = _duaType
//
//    private var _duaName = mutableStateOf("")
//    val duaName: State<String> = _duaName

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getRandomAyah()
            getRandomHadith()
            getRandomName()
            getRandomDua()
            getCurrentLocation()
            sendRequest()
            getTiming()
        }
    }

    private fun getRandomName() {
        val randomChapter = dbRepo.getRandomName()
        randomChapter.onEach {

            viewModelState.update { viewModelState ->

                viewModelState.copy(
                    name = it
                )

            }

        }.launchIn(viewModelScope)
    }

    private fun getRandomDua() {
        val randomDua = dbRepo.getRandomDua()
        randomDua.onEach { supplication ->
            supplication?.let {

                val size = it.duas.size
                val randomIndex = (0 until size).random()
                val dua = it.duas[randomIndex]
                val nSize = dua.data.size
                val nRandomIndex = (0 until nSize).random()

                viewModelState.update { viewModelState ->

                    viewModelState.copy(
                        duaType = it.name,
                        duaName = dua.name,
                        randDua = dua.data[nRandomIndex]
                    )

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRandomAyah() {
        val randomChapter = dbRepo.getRandomChapter()
        randomChapter.onEach {

            val size = it.ayahs.size
            val randomIndex = (0 until size).random()

            viewModelState.update { viewModelState ->

                viewModelState.copy(
                    chapterName = it.englishName,
                    chapterNum = it.number,
                    verse = it.ayahs[randomIndex]
                )

            }
        }.launchIn(viewModelScope)
    }

    private fun getRandomHadith() {

        val randomChapter = dbRepo.getRandomHadith()

        randomChapter.onEach {

            it?.let { item ->
                val size = item.books.size

                val randomIndex = (0 until size).random()

                val size2 = item.books[randomIndex].hadiths.size

                val randomIndex2 = (0 until size2).random()

                viewModelState.update { viewModelState ->

                    viewModelState.copy(
                        hadith = item.books[randomIndex].hadiths[randomIndex2],
                    )

                }

            }

        }.launchIn(viewModelScope)

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
                viewModelState.update { viewModelState ->

                    viewModelState.copy(
                        intent = true,
                    )

                }
            }
        } else {
            Timber.d("GetCity")

            val mLocationManager: LocationManager =
                context.getSystemService(
                    LOCATION_SERVICE
                ) as LocationManager
            val providers: List<String> = mLocationManager.getProviders(true)
            var bestLocation: Location? = null
            for (provider in providers) {
                val l: Location = mLocationManager.getLastKnownLocation(provider)
                    ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    bestLocation = l
                }
            }

            if (bestLocation == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Location Not found", Toast.LENGTH_LONG).show()
                }
            } else {
                val geocoder = Geocoder(context)
                try {
                    getCityFormLogLat(geocoder, bestLocation)
                    withContext(Dispatchers.Main) {
                        viewModelState.update { viewModelState ->

                            viewModelState.copy(
                                city = city
                            )

                        }
                    }
                    Timber.d("City Name: $city")
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

    private fun getCityFormLogLat(geocoder: Geocoder, location: Location) {
        long = location.longitude
        lat = location.latitude
        val user = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        Timber.d("Long: ${location.longitude}, Lat: ${location.latitude}")
        val lat = user[0].latitude
        val lng = user[0].longitude
        val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
        city = addresses[0].locality
    }

    private fun getTiming() {

        dbRepo.getPrayerTiming(city).onEach { list ->

            if (!list.isNullOrEmpty()) {

                list.forEachIndexed { index, prayerTimingEntity ->

                    if (currentDate == prayerTimingEntity.gregorian) {

                        val lastIndex = list.lastIndex

                        val prayerTimeEntity = if (index == lastIndex) {
                            prayerTimingEntity
                        } else {
                            list[index + 1]
                        }

                        setPrayerTimings(prayerTimingEntity, prayerTimeEntity)

                    }

                }

            }


        }.launchIn(viewModelScope)

        when (calendar[Calendar.DAY_OF_WEEK]) {

            Calendar.SUNDAY -> {
                updateValue("Sunday")
            }
            Calendar.MONDAY -> {
                updateValue("Monday")
            }
            Calendar.TUESDAY -> {
                updateValue("Tuesday")
            }
            Calendar.WEDNESDAY -> {
                updateValue("Wednesday")
            }
            Calendar.THURSDAY -> {
                updateValue( "Thursday")
            }
            Calendar.FRIDAY -> {
                updateValue("Friday")
            }
            Calendar.SATURDAY -> {
                updateValue("Saturday")
            }

        }
    }

    private fun updateValue(day: String) {
        viewModelState.update { viewModelState ->

            viewModelState.copy(
                currentDay = day
            )

        }
    }

    private suspend fun sendRequest() {

        city?.let {
            val dataByCity = dbRepo.getDataByCity(it)
            val dataByDate = dbRepo.getDataByDate(currentDate)

            if (dataByCity.isNullOrEmpty() || dataByDate.isNullOrEmpty()) {
                Timber.d("Request sent")

                val currentYear = calendar[Calendar.YEAR]

                val currentMonth = calendar[Calendar.MONTH] + 1

                repo.prayerTimingRequest(
                    long = long,
                    lat = lat,
                    currentYear,
                    currentMonth
                )
            }
        }


        when (val value = repo.response.value) {

            is DataState.Success<PrayerTiming> -> {
                Timber.d("Success")

                val prayerTiming = value.data.data

                prayerTiming.forEach { datetime ->

                    val times = datetime.timings
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
                        gregorian = date.gregorian.date,
                        hijri = date.hijri.date,
                        city = city,
                        timestamp = date.timestamp
                    )

                    repo.insertInDB(entity)
                }

            }

            is DataState.Error -> {
                Timber.d("Exception: ${value.exception}")
            }
            DataState.Idle -> {
                Timber.d("Getting timing from Database")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setPrayerTimings(
        prayerTiming: PrayerTimingEntity,
        prayerTimingNextDay: PrayerTimingEntity
    ) {

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())

        var fajr = prayerTiming.Fajr?.replace(" (PKT)", "")
        var fajrNextDay = prayerTimingNextDay.Fajr?.replace("(PKT)", "")
        var dhuhr = prayerTiming.Dhuhr?.replace("(PKT)", "")
        var asr = prayerTiming.Asr?.replace("(PKT)", "")
        var maghrib = prayerTiming.Maghrib?.replace("(PKT)", "")
        var isha = prayerTiming.Isha?.replace("(PKT)", "")
        var sunrise = prayerTiming.Sunrise?.replace("(PKT)", "")
        var midnight = prayerTimingNextDay.Midnight?.replace("(PKT)", "")

        fajr = fajr?.let { formatDate(it) }
        dhuhr = dhuhr?.let { formatDate(it) }
        asr = asr?.let { formatDate(it) }
        maghrib = maghrib?.let { formatDate(it) }
        isha = isha?.let { formatDate(it) }
        sunrise = sunrise?.let { formatDate(it) }
        midnight = midnight?.let { formatDate(it) }
        fajrNextDay = fajrNextDay?.let { formatDate(it) }

        val fajrWithDate = "${prayerTiming.gregorian} $fajr"
        val fajrNextDayWithDate =
            "${prayerTimingNextDay.gregorian} $fajrNextDay"
        val dhuhrWithDate = "${prayerTiming.gregorian} $dhuhr"
        val asrWithDate = "${prayerTiming.gregorian} $asr"
        val maghribWithDate = "${prayerTiming.gregorian} $maghrib"
        val ishaWithDate = "${prayerTiming.gregorian} $isha"
        val sunRiseWithDate = "${prayerTiming.gregorian} $sunrise"
        val midNightWithDate =
            "${prayerTimingNextDay.gregorian} $midnight"
        val islamicDate = prayerTiming.hijri

        val tempTomTime = "11:59:59"
        val tomorrowTime: String = tempTomTime.format(Date())

        val tempTime = SimpleDateFormat("hh:mm:ss")
        val todayTempTime: String = tempTime.format(Date()).toString()

        val todayTime = Calendar.getInstance()
        val currentTime = todayTime.timeInMillis

        val fajrTime = Calendar.getInstance()
        fajrTime.time = simpleDateFormat.parse(fajrWithDate)

        val dhuhrTime = Calendar.getInstance()
        dhuhrTime.time = simpleDateFormat.parse(dhuhrWithDate)

        val asrTime = Calendar.getInstance()
        asrTime.time = simpleDateFormat.parse(asrWithDate)

        val maghribTime = Calendar.getInstance()
        maghribTime.time = simpleDateFormat.parse(maghribWithDate)

        val ishaTime = Calendar.getInstance()
        ishaTime.time = simpleDateFormat.parse(ishaWithDate)

        val sunRiseTime = Calendar.getInstance()
        sunRiseTime.time = simpleDateFormat.parse(sunRiseWithDate)

        val midNightTime = Calendar.getInstance()
        midNightTime.time = simpleDateFormat.parse(midNightWithDate)

        val fajrNextDayTime = Calendar.getInstance()
        fajrNextDayTime.time = simpleDateFormat.parse(fajrNextDayWithDate)

        viewModelState.update { viewModelState ->

            viewModelState.copy(
                islamicDate = IslamicDateConverter.islamicDateFormatChange(islamicDate)
            )

        }

        currentTime.let {
            when {
                todayTempTime < tomorrowTime -> {

                    when {
                        currentTime < fajrTime.timeInMillis -> {
                            updateNextPrayerValue("Fajr - $fajr")
                        }
                        currentTime < dhuhrTime.timeInMillis -> {
                            updateNextPrayerValue("Dhuhr - $dhuhr")
                        }
                        currentTime < asrTime.timeInMillis -> {
                            updateNextPrayerValue("Asr - $asr")
                        }
                        currentTime < maghribTime.timeInMillis -> {
                            updateNextPrayerValue("Maghrib - $maghrib")
                        }
                        currentTime < ishaTime.timeInMillis -> {
                            updateNextPrayerValue("Isha - $isha")
                        }
                        currentTime < fajrNextDayTime.timeInMillis -> {
                            updateNextPrayerValue("Fajr - $fajr")
                        }
                        else -> Unit
                    }
                }
                else -> {
                    if (currentTime < fajrTime.timeInMillis) {
                        updateNextPrayerValue("Fajr - $fajrNextDay")
                    }
                }
            }

        }

        when {
            currentTime > fajrTime.timeInMillis && currentTime < sunRiseTime.timeInMillis -> {
                Timber.d("Current TIme : $currentTime")
                updateNowValue("Fajr")
            }
            currentTime > dhuhrTime.timeInMillis && currentTime < asrTime.timeInMillis -> {
                updateNowValue("Dhuhr")
            }
            currentTime > asrTime.timeInMillis && currentTime < maghribTime.timeInMillis -> {
                updateNowValue("Asr")
            }
            currentTime > maghribTime.timeInMillis && currentTime < ishaTime.timeInMillis -> {
                updateNowValue("Maghrib")
            }
            currentTime > ishaTime.timeInMillis && currentTime < midNightTime.timeInMillis -> {
                updateNowValue("Isha")
            }
            currentTime > sunRiseTime.timeInMillis && currentTime < dhuhrTime.timeInMillis -> {
                updateNowValue("Sunrise")
            }
            else -> updateNowValue("Tahajud")
        }
    }

    private fun updateNowValue(time: String) {
        viewModelState.update { viewModelState ->

            viewModelState.copy(
                now = time
            )

        }
    }

    private fun updateNextPrayerValue(time: String) {
        viewModelState.update { viewModelState ->

            viewModelState.copy(
                nextPrayer = time
            )

        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(time: String): String {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = format.parse(time)
        val format1 = Calendar.getInstance()
        format1.time = date
        val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return simpleDateFormat.format(format1.time)
    }

}
