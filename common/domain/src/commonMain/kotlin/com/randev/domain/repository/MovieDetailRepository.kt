package com.randev.domain.repository

import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_credits.CastModel
import com.randev.domain.model.movie_credits.MovieCreditsModel
import com.randev.domain.model.movie_detail.MovieDetailModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

interface MovieDetailRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailModel>>
    suspend fun getMovieCredits(movieId: Int): Flow<Resource<MovieCreditsModel>>
}