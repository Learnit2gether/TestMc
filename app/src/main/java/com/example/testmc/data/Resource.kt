package com.example.testmc.data

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<out T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
