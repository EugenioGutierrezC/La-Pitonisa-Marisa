package com.example.lapitonisamarisa.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lapitonisamarisa.data.model.HoroscopeAPIResponseModel
import com.example.lapitonisamarisa.domain.GetHoroscopeUseCase
import com.example.lapitonisamarisa.utils.Event
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HoroscopeViewModelTest {
    @RelaxedMockK
    private lateinit var getHoroscopeUseCase: GetHoroscopeUseCase

    private lateinit var horoscopeViewModel: HoroscopeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        horoscopeViewModel = HoroscopeViewModel(getHoroscopeUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenViewModelCallsGetHoroscopeRecieveOne() = runTest {
        val horoscope = HoroscopeAPIResponseModel("Leo", "2022-08-03", "Bla Bla")

        coEvery { getHoroscopeUseCase("Leo") } returns horoscope

        horoscopeViewModel.getHoroscope("Leo")

        //TODO Search how to test Event wrapper
        //assert(horoscopeViewModel.horoscopeLiveData.value == Event<HoroscopeAPIResponseModel("Leo", "2022-08-03", "Bla Bla")>)
    }
}