package com.android.weatherapp.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.data.remote.model.weather.WeatherResponse
import com.android.weatherapp.util.Converter.CODE_200
import com.android.weatherapp.util.LocationTracker
import com.android.weatherapp.util.flow.RequestState
import com.android.weatherapp.util.flow.mutableEventFlow
import com.example.socialcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val locationTracker: LocationTracker
) : ViewModel() {


    var latitude: Double? = null
    var longitude: Double? = null

    companion object {
        private const val SPLASH_DURATION_IN_MILLIS = 2500L
    }

    private val _isSplashFinished = mutableEventFlow<Boolean>()
    val isSplashFinished: SharedFlow<Boolean> = _isSplashFinished

    init {
        viewModelScope.launch {
            delay(SPLASH_DURATION_IN_MILLIS)
            _isSplashFinished.emit(true)
        }
    }

    fun loadCurrentLocationWeather() {
        viewModelScope.launch {

            locationTracker.getCurrentLocation()?.let { location ->

                _weatherData.value = RequestState.Loading
                try {
                    val response =
                        repository.getWeatherByLatLong(location.latitude, location.longitude)
                    when (response.code()) {
                        CODE_200 ->
                            if (response.isSuccessful) {
                                _weatherData.value = RequestState.Success(response.body())
                            }

                        else -> {
                            _weatherData.value = RequestState.ErrorMsg(response.message())
                        }
                    }
                } catch (e: Exception) {
                    _weatherData.value = RequestState.Error(e)
                }
            } ?: kotlin.run {
                _weatherData.value =
                    RequestState.ErrorMsg("Couldn't retrieve location. Make sure to grant permission and enable GPS.")
               getWeatherByLatLong(
                    51.5073219,
                    -0.1276474,
                )
            }
        }
    }


    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _weatherData =
        MutableStateFlow<RequestState<WeatherResponse?>>(RequestState.Idle)
    val weatherData: StateFlow<RequestState<WeatherResponse?>> = _weatherData


    fun getLatLong(city:String) {

        viewModelScope.launch {
                _weatherData.value = RequestState.Loading

                try {
                    val response = repository.getLatLong(city)
                    when (response.code()) {
                        CODE_200 ->
                            if (response.isSuccessful && response.body() != null) {
                                if(response.body()!!.isNotEmpty()){

                                    val lat = response.body()!![0].lat
                                    val lon = response.body()!![0].lon
                                    if (lat != null && lon != null) {

                                        getWeatherByLatLong(lat, lon)

                                    }

                                }
                                else{
                                    _weatherData.value = RequestState.ErrorMsg("Please Enter the Correct City name")
                                }

                            }

                        else -> {
                            _weatherData.value = RequestState.ErrorMsg(response.message())
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.toString())
                    _weatherData.value = RequestState.Error(e)

                }



        }
    }
fun setIdleState(){
     _weatherData.value = RequestState.Idle
 }
    fun getWeatherByLatLong(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherData.value = RequestState.Loading

            try {
                val response = repository.getWeatherByLatLong(lat, lon)
                when (response.code()) {
                    CODE_200 ->
                        if (response.isSuccessful) {
                            _weatherData.value = RequestState.Success(response.body())
                        }

                    else -> {
                        _weatherData.value = RequestState.ErrorMsg(response.message())
                    }
                }
            } catch (e: Exception) {
                _weatherData.value = RequestState.Error(e)
            }
        }
    }

}