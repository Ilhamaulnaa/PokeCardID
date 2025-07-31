package com.ilham.event.utils

sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(message: String, data: T? = null): Response<T>(data, message)
    class Loading<T>(data: T? = null): Response<T>(data)
}

//sealed class Response<out T> {
//    data object Loading : Response<Nothing>()
//    data object Idle : Response<Nothing>()
//    data class Success<out T>(val data: T): Response<T>()
//    data class Error<out T>(val data: T? = null, val message: String?): Response<T>()
//}
