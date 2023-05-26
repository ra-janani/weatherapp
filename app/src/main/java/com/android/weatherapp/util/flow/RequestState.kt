package com.android.weatherapp.util.flow

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()

    data class ErrorMsg(val errorMsg: String) : RequestState<Nothing>()
}
