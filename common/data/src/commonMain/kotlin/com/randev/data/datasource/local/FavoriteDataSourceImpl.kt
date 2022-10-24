package com.randev.data.datasource.local

import com.randev.movieapp_kmm.db.MovieDatabase
import com.randev.movieappkmm.db.FavoriteEntity

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class FavoriteDataSourceImpl(
    db: MovieDatabase
): FavoriteDataSource {
    private val queries = db.favoriteEntityQueries

    override fun getFavoriteAll(): List<FavoriteEntity> {
        return queries.selectFavoriteAll().executeAsList()
    }

    override fun getFavoriteById(id: Long): FavoriteEntity? {
        return queries.selectFavoriteById(id).executeAsOneOrNull()
    }

    override suspend fun deleteFavoriteById(id: Long): Long {
        queries.deleteFavoriteById(id)
        return queries.selectChanges().executeAsOne()
    }

    override suspend fun deleteFavoriteAll(): Long {
        queries.deleteFavoriteAll()
        return queries.selectChanges().executeAsOne()
    }

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Long {
        queries.insertItem(
            favoriteEntity.id,
            favoriteEntity.title,
            favoriteEntity.poster,
            favoriteEntity.overview,
            favoriteEntity.releaseDate
        )
        val id = queries.selectLastInsertedRowId().executeAsOne()
        println("Favorite -> $id")
        return id
    }
}