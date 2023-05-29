package com.android.weatherapp.data.remote.repo

import com.example.socialcompose.repository.Repository
import com.android.weatherapp.data.remote.api.ApiRequest
import com.android.weatherapp.data.remote.model.weather.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
) : Repository {

    override suspend fun getLatLong(query: String)=apiRequest.getLatLong(query)

    override suspend fun getWeatherByLatLong(lat: Double, lon: Double): Response<WeatherResponse?> =apiRequest.getWeatherByLatLong(lat,lon)


}