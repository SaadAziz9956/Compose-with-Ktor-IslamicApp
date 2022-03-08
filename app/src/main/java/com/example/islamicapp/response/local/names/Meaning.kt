package com.example.islamicapp.response.local.names

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Meaning(
    var english: String? = null,
    var urdu: String? = null
) : Parcelable