package com.example.animequotes.base.wrapper

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
sealed class DataResource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T): DataResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null): DataResource<T>(data, exception = exception)
}