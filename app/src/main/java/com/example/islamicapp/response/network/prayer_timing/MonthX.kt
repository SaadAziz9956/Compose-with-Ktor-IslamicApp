package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class MonthX(
    val ar: String,
    val en: String,
    val number: Int
)