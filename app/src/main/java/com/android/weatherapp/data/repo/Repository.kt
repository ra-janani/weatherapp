package com.example.socialcompose.repository


import com.android.weatherapp.data.remote.model.WeatherResponse
import retrofit2.Response

interface Repository {

    suspend fun getWeather(query: String): Response<WeatherResponse?>


}