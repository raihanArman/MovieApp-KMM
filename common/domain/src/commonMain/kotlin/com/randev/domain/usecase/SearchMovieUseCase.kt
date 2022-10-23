package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_list.MovieModel
import com.randev.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class SearchMovieUseCase(
    private val repository: MovieSearchRepository
): FlowUseCase<SearchRequest?, MovieModel>() {
    override suspend fun execute(parameters: SearchRequest?): Flow<Resource<MovieModel>> {
        return repository.searchMovie(parameters?.query.orEmpty(), parameters?.page ?: 1)
    }
}

data class SearchRequest(
    val query: String,
    val page: Int
)