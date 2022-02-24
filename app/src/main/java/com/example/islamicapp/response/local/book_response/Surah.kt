package com.example.islamicapp.response.local.book_response

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class Surah(
    val ayahs: List<Ayah>,
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    @PrimaryKey
    val number: Int,
    val revelationType: String
) : Parcelable