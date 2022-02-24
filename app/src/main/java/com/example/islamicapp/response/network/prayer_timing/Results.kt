package com.example.islamicapp.response.network.prayer_timing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val datetime: List<Datetime>,
    val location: Location,
    val settings: Settings
)