package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_credits.MovieCreditsModel
import com.randev.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

class GetMovieCreditsUseCase(
    private val repository: MovieDetailRepository
): FlowUseCase<Int?, MovieCreditsModel>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<MovieCreditsModel>> {
        return repository.getMovieCredits(parameters ?: 0)
    }
}