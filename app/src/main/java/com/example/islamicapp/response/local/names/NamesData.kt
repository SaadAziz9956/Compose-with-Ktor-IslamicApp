package com.example.islamicapp.response.local.names

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class NamesData(
    var inEnglish: String? = null,
    var inUrdu: String? = null,
    var long: String? = null,
    var meaning: Meaning? = null,
    @PrimaryKey
    var name: String,
    var number: Int? = null,
    var short: String? = null
): Parcelable {
    constructor(): this(name = "")
}