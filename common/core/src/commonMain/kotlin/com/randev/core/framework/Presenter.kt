package com.randev.core.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

interface Presenter<T, E> {
    fun present(coroutineScope: CoroutineScope, event: Flow<T>): StateFlow<T>
}