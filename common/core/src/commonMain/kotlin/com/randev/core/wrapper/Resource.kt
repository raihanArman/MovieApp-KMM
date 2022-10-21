package com.randev.core.wrapper


/**
 * @author Raihan Arman
 * @date 11/10/22
 */
sealed class Resource<out T> {
    data class Success<T>(val model: T? = null) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data class Idle<T>(val model: T? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
    object None : Resource<Nothing>()
}


fun <T, E>Resource<T>.convertToModel(
    convert: (E) -> Unit
) {
}