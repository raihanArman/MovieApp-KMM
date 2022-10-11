package com.randev.core.wrapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

abstract class FlowUseCase<in P, R> {
    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch { e ->
            Resource.Error(
                errorMessage = e.message ?: ""
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}