package com.example.islamicapp.response.local.names

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class NamesData(

    var inEnglish: String? = null,

    var inUrdu: String? = null,

    @SerializedName("long")
    @ColumnInfo(name = "long")
    var longDescription: String? = null,

    var meaning: Meaning? = null,

    @PrimaryKey
    var name: String,

    var number: Int? = null,

    @SerializedName("short")
    @ColumnInfo(name = "short")
    var shortDescription: String? = null

): Parcelable {
    constructor(): this(name = "")
}