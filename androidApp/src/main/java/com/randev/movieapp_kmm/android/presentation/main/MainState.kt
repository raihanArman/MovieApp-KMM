package com.randev.movieapp_kmm.android.presentation.main

import com.randev.domain.model.DataMovieModel

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

data class MainState(
    val movieList: List<DataMovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)