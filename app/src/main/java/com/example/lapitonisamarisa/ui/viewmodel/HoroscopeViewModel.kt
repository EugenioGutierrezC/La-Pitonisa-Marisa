package com.example.lapitonisamarisa.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.domain.GetHoroscopeUseCase
import com.example.lapitonisamarisa.utils.Event
import com.example.lapitonisamarisa.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
) : ViewModel() {

    val horoscopeLiveData = MutableLiveData<Event<HoroscopeAPIResponseModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getHoroscope(sign: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val horoscope = getHoroscopeUseCase(Utils.descapitalizeFirstChar(sign))
            horoscopeLiveData.value = Event(horoscope)
            isLoading.postValue(false)
        }
    }
}