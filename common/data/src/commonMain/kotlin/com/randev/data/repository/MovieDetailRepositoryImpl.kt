package com.randev.data.repository

import com.randev.core.wrapper.NetworkResource
import com.randev.core.wrapper.Resource
import com.randev.data.datasource.remote.MovieApi
import com.randev.data.mapper.MovieDetailMapper
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 20/10/22
 */
class MovieDetailRepositoryImpl(
    private val mapper: MovieDetailMapper,
    private val api: MovieApi
): MovieDetailRepository {
    override suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailModel>> {
        return object : NetworkResource<MovieDetailModel>() {
            override suspend fun remoteFetch(): MovieDetailModel {
                val request = api.fetchMovieDetail(movieId)
                return mapper.map(request)
            }

        }.asFlow()
    }
}