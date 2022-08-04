package com.example.lapitonisamarisa.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun descapitalizeFirstChar(word: String): String {
        return word.replaceFirstChar { it.lowercase() }
    }

    fun currentDate(): String {
        val formatter =  SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(Date())
    }
}