package com.randev.data.datasource.local

import com.randev.movieapp_kmm.db.MovieDatabase
import com.randev.movieappkmm.db.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * @author Raihan Arman
 * @date 18/10/22
 */

class MovieDataSourceImpl(
    db: MovieDatabase
): MovieDataSource {
    private val queries = db.movieEntityQueries

    override fun getAllMovies(): List<MovieEntity> {
        return queries.selectMovieAll().executeAsList()
    }

    override fun getMovieById(id: Long): MovieEntity? {
        return queries.selectMovieById(id).executeAsOneOrNull()
    }

    override suspend fun deleteMovieById(id: Long) {
        queries.deleteMovieById(id)
    }

    override suspend fun deleteMovieAll() {
        queries.deleteMovieAll()
    }

    override suspend fun insertMovie(movieEntity: MovieEntity) {
        queries.insertItem(
            movieEntity.id,
            movieEntity.title,
            movieEntity.poster,
            movieEntity.overview,
            movieEntity.releaseDate
        )
    }

}