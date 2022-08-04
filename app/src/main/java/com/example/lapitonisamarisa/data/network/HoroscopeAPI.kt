package com.example.lapitonisamarisa.data.network

import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeAPI {

    @GET("{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): Response<HoroscopeAPIResponseModel>
}