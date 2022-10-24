package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class GetFavoriteUseCase(
    private val repository: FavoriteRepository
): FlowUseCase<Nothing?, List<FavoriteModel>>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<List<FavoriteModel>>> {
        return repository.getFavorite()
    }
}