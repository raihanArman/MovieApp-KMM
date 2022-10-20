package com.randev.movieapp_kmm.android.presentation.detail

import com.randev.domain.model.movie_detail.MovieDetailModel

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

data class DetailState(
    val movieDetail: MovieDetailModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
