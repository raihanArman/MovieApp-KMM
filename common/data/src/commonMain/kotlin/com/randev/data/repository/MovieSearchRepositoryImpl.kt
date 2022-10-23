package com.randev.data.repository

import com.randev.core.wrapper.NetworkResource
import com.randev.core.wrapper.Resource
import com.randev.data.datasource.remote.MovieApi
import com.randev.data.mapper.MovieMapper
import com.randev.domain.model.movie_list.MovieModel
import com.randev.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class MovieSearchRepositoryImpl(
    private val mapper: MovieMapper,
    private val api: MovieApi,
): MovieSearchRepository {
    override suspend fun searchMovie(query: String, page: Int): Flow<Resource<MovieModel>> {
        return object : NetworkResource<MovieModel>() {
            override suspend fun remoteFetch(): MovieModel {
                val request = api.fetchMovieSearch(query, page)
                return mapper.map(request)
            }
        }.asFlow()
    }
}