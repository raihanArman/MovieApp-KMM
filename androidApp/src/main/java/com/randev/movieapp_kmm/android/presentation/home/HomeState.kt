package com.randev.movieapp_kmm.android.presentation.home

import com.randev.domain.model.movie_list.DataMovieModel

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

data class HomeState(
    val movieList: List<DataMovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)