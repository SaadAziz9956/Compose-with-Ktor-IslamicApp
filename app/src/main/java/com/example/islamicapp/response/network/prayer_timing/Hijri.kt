package com.example.islamicapp.response.network.prayer_timing

import kotlinx.serialization.Serializable

@Serializable
data class Hijri(
    val date: String,
    val day: String,
    val designation: DesignationX,
    val format: String,
    val holidays: List<String>,
    val month: MonthX,
    val weekday: WeekdayX,
    val year: String
)