package com.randev.data.remote

import com.randev.data.response.MovieResponse

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

interface MovieApiClient {
    suspend fun fetchMovie(): MovieResponse
}
