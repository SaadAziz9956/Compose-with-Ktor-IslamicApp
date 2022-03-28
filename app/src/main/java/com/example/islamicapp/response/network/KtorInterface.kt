package com.example.islamicapp.response.network

import com.example.islamicapp.response.network.chapter_detail_response.ChapterDetailResponse
import com.example.islamicapp.response.network.prayer_timing.PrayerTiming
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface KtorInterface {

    suspend fun getResponse(int: String): ChapterDetailResponse

    suspend fun getPrayerTiming(long: Double?, lat: Double?, currentYear: Int, currentMonth: Int): PrayerTiming

    companion object {
        fun create(): KtorInterface {
            return KtorImplementation(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

}