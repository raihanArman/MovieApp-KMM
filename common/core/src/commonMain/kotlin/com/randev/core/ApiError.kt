package com.randev.core

data class ApiError(
    val errorTitle: String,
    val errorMessage: String
): Exception()