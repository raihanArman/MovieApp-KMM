package com.randev.domain.model.movie_list

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
