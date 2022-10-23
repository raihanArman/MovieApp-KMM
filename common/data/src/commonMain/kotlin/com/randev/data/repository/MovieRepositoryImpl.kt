package com.randev.data.repository

import com.randev.core.arch.BaseRepositoryImpl
import com.randev.core.wrapper.NetworkResource
import com.randev.core.wrapper.Resource
import com.randev.data.datasource.local.MovieDataSource
import com.randev.data.mapper.MovieMapper
import com.randev.data.datasource.remote.MovieApi
import com.randev.data.mapper.MovieDetailMapper
import com.randev.data.mapper.mapToDataMovieModel
import com.randev.data.mapper.mapToMovieEntity
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.model.movie_list.MovieModel
import com.randev.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

class MovieRepositoryImpl(
    private val mapper: MovieMapper,
    private val movieDataSource: MovieDataSource,
    private val api: MovieApi
): MovieRepository, BaseRepositoryImpl() {
    override suspend fun getMovie(page: Int): Flow<Resource<MovieModel>> {
        return object : NetworkResource<MovieModel>() {

            override suspend fun remoteFetch(): MovieModel {
                val request = api.fetchMovie(page)
                return mapper.map(request)
            }

            override suspend fun saveLocal(data: MovieModel) {
                movieDataSource.deleteMovieAll()
                data.results.forEach {
                    movieDataSource.insertMovie(
                        it.mapToMovieEntity()
                    )
                }
            }

            override suspend fun localFetch(): MovieModel {
                val data = movieDataSource.getAllMovies()
                return MovieModel(
                    page = 0,
                    results = data.map {
                        it.mapToDataMovieModel()
                    },
                    totalPages = 0,
                    totalResults = data.size
                )
            }

            override fun shouldFetchRemoteAndSaveLocal(): Boolean {
                return true
            }

            override fun shouldFetchLocalOnError(): Boolean {
                return true
            }

        }.asFlow()
    }

    override suspend fun getMovieUpcoming(page: Int): Flow<Resource<MovieModel>> {
        return object : NetworkResource<MovieModel>() {
            override suspend fun remoteFetch(): MovieModel {
                val request = api.fetchMovieUpcoming(page)
                return mapper.map(request)
            }
        }.asFlow()
    }
}