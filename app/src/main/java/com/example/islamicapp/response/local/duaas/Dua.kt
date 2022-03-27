package com.example.islamicapp.response.local.duaas

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Dua(
    val `data`: List<DuaaData>,
    val name: String
) : Parcelable