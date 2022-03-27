package com.example.islamicapp.response.local.book_response

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Ayah(
    val audio: String? = null,
    val audioSecondary: List<String>? = null,
    val hizbQuarter: Int? = null,
    val juz: Int? = null,
    val manzil: Int? = null,
    val number: Int? = null,
    val numberInSurah: Int? = null,
    val page: Int? = null,
    val ruku: Int? = null,
    val sajda: Any? = null,
    val text: String? = null,
    var textTranslated: String? = null,
    var englishTrans: String? = null,
) : Serializable