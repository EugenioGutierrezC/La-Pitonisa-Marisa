package com.example.lapitonisamarisa.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.domain.GetHoroscopeUseCase
import com.example.lapitonisamarisa.utils.Event
import com.example.lapitonisamarisa.utils.Exceptions.ApiException
import com.example.lapitonisamarisa.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeResultViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
) : ViewModel() {

    val horoscopeLiveData = MutableLiveData<Event<HoroscopeAPIResponseModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val popUpLaunch = MutableLiveData<Event<String>>()

    fun getHoroscope(sign: String) {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                val horoscope = getHoroscopeUseCase(Utils.descapitalizeFirstChar(sign))
                horoscopeLiveData.value = Event(horoscope)
                isLoading.postValue(false)
            } catch (e: ApiException) {
                isLoading.postValue(false)
                popUpLaunch.postValue(Event(e.toString()))
                e.printStackTrace()
            }
            //TODO catch here when internet is not available.
        }
    }
}