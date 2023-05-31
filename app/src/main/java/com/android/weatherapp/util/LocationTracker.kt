package com.android.weatherapp.util

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?

}