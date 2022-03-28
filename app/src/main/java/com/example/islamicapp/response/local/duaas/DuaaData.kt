package com.example.islamicapp.response.local.duaas

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DuaaData(
    val inEnglish: String,
    val meaning: String,
    val reference: String,
    val text: String
) : Parcelable