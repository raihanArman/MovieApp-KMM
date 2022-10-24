package com.randev.domain.repository

import com.randev.core.wrapper.Resource
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.model.movie_list.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

interface FavoriteRepository {
    suspend fun getFavorite(): Flow<Resource<List<FavoriteModel>>>
    suspend fun insertFavorite(
        id: Long,
        title: String,
        poster: String,
        overview: String,
        releaseDate: String,
    ): Flow<Resource<Long>>
    suspend fun deleteFavorite(id: Long): Flow<Resource<Long>>
}