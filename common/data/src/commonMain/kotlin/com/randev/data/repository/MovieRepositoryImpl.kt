package com.randev.data.repository

import com.randev.core.arch.BaseRepositoryImpl
import com.randev.core.wrapper.NetworkResource
import com.randev.core.wrapper.Resource
import com.randev.data.mapper.MovieMapper
import com.randev.data.remote.MovieApi
import com.randev.domain.model.MovieModel
import com.randev.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 11/10/22
 */
class MovieRepositoryImpl(
    private val mapper: MovieMapper,
    private val api: MovieApi
): MovieRepository, BaseRepositoryImpl() {
    override suspend fun getMovie(): Flow<Resource<MovieModel>> {
        return object : NetworkResource<MovieModel>() {
            override suspend fun remoteFetch(): MovieModel {
                val request = api.fetchMovie()
                return mapper.map(request)
            }

        }.asFlow()
    }
}