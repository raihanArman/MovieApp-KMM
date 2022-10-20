package com.randev.data.datasource.remote

import com.randev.data.response.movie_details.MovieDetailResponse
import com.randev.data.response.movie_list.MovieResponse

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

interface MovieApiClient {
    suspend fun fetchMovie(page: Int): MovieResponse
    suspend fun fetchMovieDetail(movieId: Int): MovieDetailResponse
}
