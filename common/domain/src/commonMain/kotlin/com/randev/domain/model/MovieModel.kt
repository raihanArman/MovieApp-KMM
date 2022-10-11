package com.randev.domain.model

import kotlinx.serialization.SerialName

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

data class MovieModel(
    val page: Int,
    val results: List<DataMovieModel>,
    val totalPages: Int,
    val totalResults: Int,
)
