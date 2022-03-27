package com.example.islamicapp.response.local.duaas

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DuaaData(
    val inEnglish: String? = null,
    val meaning: String? = null,
    val reference: String? = null,
    val text: String? = null
) : Parcelable