package com.example.lapitonisamarisa.data.network

import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.utils.Exceptions.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HoroscopeService @Inject constructor(private val api: HoroscopeAPI) {

    suspend fun getHoroscope(sign: String): HoroscopeAPIResponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getHoroscope(sign)
            response.body()
                ?: throw ApiException(response.message() + " Status code: " + response.code())
        }
    }
}