package com.randev.data.datasource.remote

import com.randev.data.response.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

class MovieApi(
    private val ktor: HttpClient
): MovieApiClient {
    override suspend fun fetchMovie(page: Int): MovieResponse {
        return ktor.get("3/movie/popular") {
            parameter("api_key", "0027a40637be10414a5f22100bb4dda8")
            parameter("page", page)
        }.body()
    }
}