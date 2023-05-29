package com.example.socialcompose.repository


import com.android.weatherapp.data.remote.model.geo_location.GeoLocationResponse
import com.android.weatherapp.data.remote.model.weather.WeatherResponse
import retrofit2.Response

interface Repository {

    suspend fun getLatLong(query: String): Response<GeoLocationResponse?>

    suspend fun getWeatherByLatLong(lat: Double,lon: Double): Response<WeatherResponse?>


}