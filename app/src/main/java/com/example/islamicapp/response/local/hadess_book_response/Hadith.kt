package com.example.islamicapp.response.local.hadess_book_response

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Hadith(
    val `by`: String,
    val info: String,
    val text: String
): Parcelable