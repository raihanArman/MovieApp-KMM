package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

class GetMovieDetailUseCase(
    private val repository: MovieDetailRepository
): FlowUseCase<Int?, MovieDetailModel>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<MovieDetailModel>> {
        return repository.getMovieDetails(parameters ?: 0)
    }
}