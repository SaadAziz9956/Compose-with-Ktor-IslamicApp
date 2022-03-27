package com.example.islamicapp.response.local.duaas

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class Supplication(
    val duas: List<Dua>,
    @PrimaryKey
    val name: String
) : Parcelable