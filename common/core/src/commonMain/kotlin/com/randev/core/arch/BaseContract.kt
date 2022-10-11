package com.randev.core.arch

import kotlin.Exception

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
interface BaseContract {
    interface BaseRepository {
        fun logResponse(msg: String?)
    }
}