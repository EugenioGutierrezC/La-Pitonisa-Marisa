package com.example.lapitonisamarisa.domain

import com.example.lapitonisamarisa.data.HoroscopeRepository
import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.utils.Utils
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {
    suspend operator fun invoke(sign: String): HoroscopeAPIResponseModel {

        val horoscope = repository.getHoroscope(sign)

        return if (!horoscope.horoscope.isBlank()) {
            horoscope
        } else {
            //TODO Get random data from database to fill the horoscope. Meanwhile return empty horoscope
            HoroscopeAPIResponseModel("Ophiuchus",Utils.currentDate(), "Always gonna be nope")
        }
    }
}