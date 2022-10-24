package com.randev.movieapp_kmm.android.presentation.favorite

import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.model.movie_credits.CastModel
import com.randev.domain.model.movie_detail.MovieDetailModel

/**
 * @author Raihan Arman
 * @date 24/10/22
 */

data class FavoriteState(
    val favoriteList: List<FavoriteModel>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
