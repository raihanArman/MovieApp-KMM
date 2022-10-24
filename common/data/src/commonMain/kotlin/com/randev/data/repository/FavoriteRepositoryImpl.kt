package com.randev.data.repository

import com.randev.core.wrapper.Resource
import com.randev.data.datasource.local.FavoriteDataSource
import com.randev.data.mapper.FavoriteMapper
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.repository.FavoriteRepository
import com.randev.movieappkmm.db.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource,
    private val mapper: FavoriteMapper
): FavoriteRepository {
    override suspend fun getFavorite(): Flow<Resource<List<FavoriteModel>>> {
        return flow {
            emit(
                Resource.Success(
                    favoriteDataSource.getFavoriteAll().map {
                        mapper.map(it)
                    }
                )
            )
        }
    }

    override suspend fun insertFavorite(
        id: Long,
        title: String,
        poster: String,
        overview: String,
        releaseDate: String
    ): Flow<Resource<Long>> {
        return flow {
            val result = favoriteDataSource.insertFavorite(
                favoriteEntity = FavoriteEntity(
                    id = id,
                    title = title,
                    poster = poster,
                    overview = overview,
                    releaseDate = releaseDate
                )
            )
            println("Result -> $result")
            emit(
                Resource.Success(
                    result
                )
            )
        }
    }

    override suspend fun deleteFavorite(id: Long): Flow<Resource<Long>> {
        return flow {
            emit(
                Resource.Success(favoriteDataSource.deleteFavoriteById(id))
            )
        }
    }
}