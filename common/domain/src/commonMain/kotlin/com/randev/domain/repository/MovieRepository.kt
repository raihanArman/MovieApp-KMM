package com.randev.domain.repository

import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.model.movie_list.MovieModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Raihan Arman
 * @date 29/08/22
 */

interface MovieRepository {
    suspend fun getMovie(page: Int): Flow<Resource<MovieModel>>
}