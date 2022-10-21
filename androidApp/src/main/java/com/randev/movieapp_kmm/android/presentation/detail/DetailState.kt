package com.randev.movieapp_kmm.android.presentation.detail

import com.randev.domain.model.movie_credits.CastModel
import com.randev.domain.model.movie_detail.MovieDetailModel

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

data class DetailState(
    val movieDetail: MovieDetailModel? = null,
    val castList: List<CastModel> ?= emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    companion object {
        fun empty() = DetailState(
            movieDetail = null,
            castList =  emptyList(),
            isLoading = false,
            errorMessage = null
        )
    }
}
