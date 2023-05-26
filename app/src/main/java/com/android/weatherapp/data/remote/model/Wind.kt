package com.android.weatherapp.data.remote.model


import androidx.annotation.Keep

@Keep
data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)