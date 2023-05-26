package com.example.socialcompose.repository


import com.android.weatherapp.data.remote.model.WeatherResponse

interface Repository {

    suspend fun getWeather(query: String): WeatherResponse?


}