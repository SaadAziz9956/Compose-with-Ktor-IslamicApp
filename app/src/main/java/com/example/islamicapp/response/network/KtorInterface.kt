package com.example.islamicapp.response.network

import com.example.expensemanagment.response.network.chapter_detail_response.ChapterDetailResponse
import com.example.expensemanagment.response.network.prayer_timing.PrayerTiming
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface KtorInterface {

    suspend fun getResponse(int: String): ChapterDetailResponse

    suspend fun getPrayerTiming(city: String): PrayerTiming

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