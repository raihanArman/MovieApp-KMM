package com.randev.domain.model.movie_detail

import kotlinx.serialization.SerialName

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

data class MovieDetailModel(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<GenreModel?>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val status: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavorite: Boolean = false
)

data class GenreModel(
    val id: Int,
    val name: String
)