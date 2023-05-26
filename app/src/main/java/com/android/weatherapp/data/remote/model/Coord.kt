package com.android.weatherapp.data.remote.model


import androidx.annotation.Keep

@Keep
data class Coord(
    val lat: Double?,
    val lon: Double?
)