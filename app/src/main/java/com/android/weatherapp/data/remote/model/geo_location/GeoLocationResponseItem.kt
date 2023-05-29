package com.android.weatherapp.data.remote.model.geo_location


import androidx.annotation.Keep

@Keep
data class GeoLocationResponseItem(
    val country: String?,
    val lat: Double?,
    val local_names: LocalNames?,
    val lon: Double?,
    val name: String?,
    val state: String?
)