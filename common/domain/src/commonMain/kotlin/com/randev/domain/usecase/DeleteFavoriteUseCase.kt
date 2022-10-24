package com.randev.domain.usecase

import com.randev.core.wrapper.FlowUseCase
import com.randev.core.wrapper.Resource
import com.randev.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 24/10/22
 */

class DeleteFavoriteUseCase(
    private val repository: FavoriteRepository
): FlowUseCase<Int?, Long>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<Long>> {
        return repository.deleteFavorite(parameters!!.toLong())
    }
}