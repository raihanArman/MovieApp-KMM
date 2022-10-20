package com.randev.data.response.movie_list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

@Serializable
data class MovieResultResponse(
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
)