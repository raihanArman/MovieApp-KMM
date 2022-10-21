package com.randev.domain.model.movie_credits

import kotlinx.serialization.SerialName

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

data class MovieCreditsModel(
    val castList: List<CastModel>
)

data class CastModel(
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String
)
