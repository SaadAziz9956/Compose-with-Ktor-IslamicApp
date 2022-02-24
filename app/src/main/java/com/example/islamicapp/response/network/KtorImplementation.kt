package com.example.islamicapp.response.network

import com.example.islamicapp.response.network.chapter_detail_response.ChapterDetailResponse
import com.example.islamicapp.response.network.prayer_timing.PrayerTiming
import com.example.islamicapp.Constants.KTOR_BASE_URL
import com.example.islamicapp.Constants.PRAYER_TIMING_BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*

class KtorImplementation(
    private val client: HttpClient
) : KtorInterface {

    override suspend fun getResponse(int: String): ChapterDetailResponse {
        return client.get {
            url("$KTOR_BASE_URL$int/info")
            parameter("language", "ur")
        }
    }

    override suspend fun getPrayerTiming(city: String): PrayerTiming {
        return client.get {
            url(PRAYER_TIMING_BASE_URL)
            parameter("city", city)
            parameter("school", 1)
            parameter("juristic", 1)
            parameter("timeformat", 1)
        }
    }

}