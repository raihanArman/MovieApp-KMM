package com.randev.core.arch

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

abstract class BaseUseCase<P, R: Any?> {
    suspend operator fun invoke(param: P? = null,dispatcher: CoroutineDispatcher?): Flow<R> {
        return execute(param)
            .catch {
                throw IllegalArgumentException("Error")
            }
            .flowOn(dispatcher?:Dispatchers.Main)
    }

    @Throws(RuntimeException::class)
    @NativeCoroutinesIgnore
    abstract suspend fun execute(param: P? = null): Flow<R>

}