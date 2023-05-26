package com.android.weatherapp.data.remote.repo

import com.example.socialcompose.repository.Repository
import com.android.weatherapp.data.remote.api.ApiRequest
import com.android.weatherapp.data.remote.model.WeatherResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
) : Repository {

    override suspend fun getWeather(query: String) = apiRequest.getWeather(query)


}