package com.randev.core.exception

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
sealed class ViewResource<T>(
    val data: T?= null,
    val message:String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T): ViewResource<T>(data)
    class Empty<T>(data: T? = null): ViewResource<T>(data)
    class Loading<T>(data: T? = null): ViewResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null): ViewResource<T>(data, exception = exception)
}