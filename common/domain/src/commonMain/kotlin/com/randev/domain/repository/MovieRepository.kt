package com.randev.domain.repository

import com.randev.core.DataResource
import com.randev.core.wrapper.Resource
import com.randev.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Raihan Arman
 * @date 29/08/22
 */

interface MovieRepository {
    suspend fun getMovie(): Flow<Resource<MovieModel>>
}