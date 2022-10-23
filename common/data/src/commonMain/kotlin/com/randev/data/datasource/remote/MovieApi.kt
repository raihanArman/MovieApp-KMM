package com.randev.data.datasource.remote

import com.randev.data.response.movie_credits.MovieCreditsResponse
import com.randev.data.response.movie_details.MovieDetailResponse
import com.randev.data.response.movie_list.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

class MovieApi(
    private val ktor: HttpClient
): MovieApiClient {

    companion object {
        const val API_KEY = "0027a40637be10414a5f22100bb4dda8"
    }

    override suspend fun fetchMovie(page: Int): MovieResponse {
        return ktor.get("3/movie/popular") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()
    }

    override suspend fun fetchMovieUpcoming(page: Int): MovieResponse {
        return ktor.get("3/movie/upcoming") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()
    }

    override suspend fun fetchMovieDetail(movieId: Int): MovieDetailResponse {
        return ktor.get("3/movie/$movieId") {
            parameter("api_key", API_KEY)
        }.body()
    }

    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
        return ktor.get("3/movie/$movieId/credits") {
            parameter("api_key", API_KEY)
        }.body()
    }

    override suspend fun fetchMovieSearch(query: String, page: Int): MovieResponse {
        return ktor.get("3/search/movie") {
            parameter("api_key", API_KEY)
            parameter("page", page)
            parameter("query", query)
        }.body()
    }
}