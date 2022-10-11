package com.randev.core.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiException(
    @SerialName("error")
    val errorTitle: String,
    @SerialName("error_description")
    val errorMessage: String
): Exception()