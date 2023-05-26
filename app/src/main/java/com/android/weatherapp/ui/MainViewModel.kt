package com.android.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.data.remote.model.WeatherResponse
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
    private val repository: Repository
) : ViewModel() {


    var latitude: String? = null
    var longitude: String? = null

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


    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _weatherData =
        MutableStateFlow<RequestState<WeatherResponse?>>(RequestState.Idle)
    val weatherData: StateFlow<RequestState<WeatherResponse?>> = _weatherData

    fun getWeather(city: String) {

        viewModelScope.launch {
            _weatherData.value = RequestState.Loading

            try {
                viewModelScope.launch {
                    val response = repository.getWeather(city)
                    if (response != null) {
                        _weatherData.value = RequestState.Success(response)
                    }
                    Log.d("MainViewModel", "response:::::    $response")
                }
            } catch (e: Exception) {
                _weatherData.value = RequestState.Error(e)
            }
        }
    }

}