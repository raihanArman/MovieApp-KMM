package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.repository.FavoriteRepository
import com.randev.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class InsertFavoriteUseCase(
    private val repository: FavoriteRepository
): FlowUseCase<FavoriteParams?, Long>(){
    override suspend fun execute(parameters: FavoriteParams?): Flow<Resource<Long>> {
        return repository.insertFavorite(
            id = parameters?.id ?: 0,
            title = parameters?.title.orEmpty(),
            poster = parameters?.poster.orEmpty(),
            overview = parameters?.overview.orEmpty(),
            releaseDate = parameters?.releaseDate.orEmpty()
        )
    }
}

data class FavoriteParams(
    val id: Long,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String
)