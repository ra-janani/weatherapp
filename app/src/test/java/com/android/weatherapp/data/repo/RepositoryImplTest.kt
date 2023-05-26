package com.android.weatherapp.data.repo

import com.android.weatherapp.data.remote.api.ApiRequest
import com.android.weatherapp.data.remote.model.Main
import com.android.weatherapp.data.remote.model.WeatherResponse
import com.android.weatherapp.data.remote.repo.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryImplTest {


    @Mock
    lateinit var apiRequest: ApiRequest

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun getWeatherCorrectCityName() = runTest {
        val weatherResult = WeatherResponse(
            "stations",
            clouds = null,
            cod = 0,
            coord = null,
            dt = 0,
            id = 0,
            name = "berlin",
            sys = null,
            timezone = 0,
            visibility = 0,
            wind = null,
            weather = emptyList(),
            main = Main(36.64, 982, 41, 1004, 1004, 34.53, 34.53, 31.53)
        )

        Mockito.`when`(apiRequest.getWeather("berlin")).thenReturn(weatherResult)


        val sut = RepositoryImpl(apiRequest)
        val result = sut.getWeather("berlin")
        Assert.assertEquals("berlin", result?.name)
    }

    @Test
    fun getWeatherMaxTempIsGreaterThanMinTemp() = runTest {
        val weatherResult = WeatherResponse(
            "stations",
            clouds = null,
            cod = 0,
            coord = null,
            dt = 0,
            id = 0,
            name = "berlin",
            sys = null,
            timezone = 0,
            visibility = 0,
            wind = null,
            weather = emptyList(),
            main = Main(36.64, 982, 41, 1004, 1004, 34.53, 34.53, 31.53)
        )

        Mockito.`when`(apiRequest.getWeather("berlin")).thenReturn(weatherResult)


        val sut = RepositoryImpl(apiRequest)
        val result = sut.getWeather("berlin")
        val check = result?.main?.temp_max?.toInt()!! > result?.main?.temp_min?.toInt()!!
        Assert.assertEquals(true, check)
    }


}