package com.android.weatherapp.data.remote.api

import com.android.weatherapp.data.remote.model.weather.WeatherResponse
import com.android.weatherapp.data.remote.model.geo_location.GeoLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {


    @GET(ApiDetails.GEO)
    suspend fun getLatLong(@Query("q") cityName: String): Response<GeoLocationResponse?>

    @GET(ApiDetails.WEATHER)
    suspend fun getWeatherByLatLong(@Query("lat") lat: Double,@Query("lon") lon: Double): Response<WeatherResponse?>

}