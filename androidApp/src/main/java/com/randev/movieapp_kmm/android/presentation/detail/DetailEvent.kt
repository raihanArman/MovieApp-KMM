package com.randev.movieapp_kmm.android.presentation.detail

import com.randev.domain.model.movie_detail.MovieDetailModel

/**
 * @author Raihan Arman
 * @date 21/10/22
 */
sealed class DetailEvent {
    class FavoriteMovieClicked(
        val movieDetailModel: MovieDetailModel
    ) : DetailEvent()
}