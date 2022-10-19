package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.MovieModel
import com.randev.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

class GetMovieUseCase(
    private val repository: MovieRepository
): FlowUseCase<Int?, MovieModel>(){
    override suspend fun execute(parameters: Int?): Flow<Resource<MovieModel>> {
        return repository.getMovie(parameters ?: 0)
    }
}