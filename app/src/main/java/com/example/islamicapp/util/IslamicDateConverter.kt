package com.example.islamicapp.util

import java.util.*

object IslamicDateConverter {

    fun islamicDateFormatChange(date: String): String {

        val list = listOf(
            "Muharram",
            "Safar",
            "Rabi-ul-Awwal",
            "Rabi-us Sani",
            "Jamadi-ul-Awwal",
            "Jamadi-us-Sani",
            "Rajab",
            "Shaban",
            "Ramadan",
            "Shawal",
            "Zil-Qadah",
            "Zul-Hijah"
        )

        var year: String? = null
        var month: String? = null
        var day: String? = null

        if (date.contains("-")) {
            val stringTokenizer = StringTokenizer(date, "-")
            val arrayOfString = arrayOfNulls<String>(stringTokenizer.countTokens())
            var i = 0
            while (stringTokenizer.hasMoreTokens()) {
                arrayOfString[i] = stringTokenizer.nextToken()
                i++
            }
            year = arrayOfString[0]
            month = arrayOfString[1]
            day = arrayOfString[2]

        }

        when (month) {
            "01" -> {
                month = list[0]
            }
            "02" -> {
                month = list[1]
            }
            "03" -> {
                month = list[2]
            }
            "04" -> {
                month = list[3]
            }
            "05" -> {
                month = list[4]
            }
            "06" -> {
                month = list[5]
            }
            "07" -> {
                month = list[6]
            }
            "08" -> {
                month = list[7]
            }
            "09" -> {
                month = list[8]
            }
            "10" -> {
                month = list[9]
            }
            "11" -> {
                month = list[10]
            }
            "12" -> {
                month = list[11]
            }
        }

        return "$day - $month - $year"

    }

}