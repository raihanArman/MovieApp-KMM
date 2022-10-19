package com.randev.data.datasource.local

import com.randev.movieappkmm.db.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 18/10/22
 */
interface MovieDataSource {
    fun getAllMovies(): List<MovieEntity>

    fun getMovieById(id: Long): MovieEntity?

    suspend fun deleteMovieById(id: Long)

    suspend fun deleteMovieAll()

    suspend fun insertMovie(movieEntity: MovieEntity)
}