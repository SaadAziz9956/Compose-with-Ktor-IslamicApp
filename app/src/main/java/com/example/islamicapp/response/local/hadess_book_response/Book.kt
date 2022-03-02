package com.example.islamicapp.response.local.hadess_book_response

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Book(
    val hadiths: List<Hadith>,
    val name: String
): Parcelable