package com.example.lapitonisamarisa.data

import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.data.network.HoroscopeService
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(
    private val api: HoroscopeService
) {

    suspend fun getHoroscope(sign: String): HoroscopeAPIResponseModel {
        val response: HoroscopeAPIResponseModel = api.getHoroscope(sign)
        return response
    }
}