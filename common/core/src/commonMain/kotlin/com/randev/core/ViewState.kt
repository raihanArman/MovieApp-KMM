package com.randev.core

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

data class ViewState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
) {

    companion object {

        fun <T> error(
            message: String,
        ): ViewState<T> {
            return ViewState(
                message = message,
                data = null,
            )
        }

        fun <T> data(
            message: String? = null,
            data: T? = null,
        ): ViewState<T> {
            return ViewState(
                message = message,
                data = data,
            )
        }

        fun <T>loading() = ViewState<T>(isLoading = true)
    }
}