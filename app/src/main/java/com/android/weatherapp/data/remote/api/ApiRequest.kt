package com.android.weatherapp.data.remote.api

import com.android.weatherapp.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {


    @GET(ApiDetails.WEATHER)
    suspend fun getWeather(@Query("q") cityName: String = "London"): WeatherResponse?


}