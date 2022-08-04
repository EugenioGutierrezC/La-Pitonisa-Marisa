package com.example.lapitonisamarisa.di

import com.example.lapitonisamarisa.data.network.HoroscopeAPI
import com.example.lapitonisamarisa.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHoroscopeApiClient(retrofit: Retrofit): HoroscopeAPI {
        return retrofit.create(HoroscopeAPI::class.java)
    }
}