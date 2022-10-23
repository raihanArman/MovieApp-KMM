package com.randev.domain.repository

import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_list.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

interface MovieSearchRepository {
    suspend fun searchMovie(query: String, page: Int): Flow<Resource<MovieModel>>
}