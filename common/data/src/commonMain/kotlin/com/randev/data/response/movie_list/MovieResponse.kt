package com.randev.data.response.movie_list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

@Serializable
data class MovieResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieResultResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,

    )