package com.randev.data.datasource.local

import com.randev.movieappkmm.db.FavoriteEntity


/**
 * @author Raihan Arman
 * @date 23/10/22
 */

interface FavoriteDataSource {
    fun getFavoriteAll(): List<FavoriteEntity>

    fun getFavoriteById(id: Long): FavoriteEntity?

    suspend fun deleteFavoriteById(id: Long): Long

    suspend fun deleteFavoriteAll(): Long

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Long
}