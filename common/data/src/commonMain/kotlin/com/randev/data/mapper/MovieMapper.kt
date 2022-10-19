package com.randev.data.mapper

import com.randev.core.arch.BaseMapper
import com.randev.data.response.MovieResponse
import com.randev.data.response.MovieResultResponse
import com.randev.domain.model.DataMovieModel
import com.randev.domain.model.MovieModel
import com.randev.movieappkmm.db.MovieEntity

/**
 * @author Raihan Arman
 * @date 11/10/22
 */
class MovieMapper: BaseMapper<MovieResponse, MovieModel>() {
    companion object {
    }
    override fun map(value: MovieResponse): MovieModel {
        return MovieModel(
            page = value.page,
            results = value.results.map {
                DataMovieModel(
                    adult = it.adult ?: false,
                    backdropPath = it.backdropPath.orEmpty(),
                    genreIds = it.genreIds,
                    id = it.id ?: 0,
                    originalLanguage = it.originalLanguage.orEmpty(),
                    originalTitle = it.originalTitle.orEmpty(),
                    overview = it.overview.orEmpty(),
                    popularity = it.popularity ?: 0.0,
                    posterPath = it.posterPath.orEmpty(),
                    releaseDate = it.releaseDate.orEmpty(),
                    title = it.title.orEmpty(),
                    video = it.video ?: false,
                    voteAverage = it.voteAverage ?: 0.0,
                )
            },
            totalPages = value.totalPages,
            totalResults = value.totalResults
        )
    }
}

fun DataMovieModel.mapToMovieEntity() = MovieEntity(
    id = this.id.toLong(),
    title = this.originalTitle,
    poster = this.posterPath,
    overview = this.overview,
    releaseDate = this.releaseDate
)

fun MovieEntity.mapToDataMovieModel() = DataMovieModel(
    adult = false,
    backdropPath = "",
    genreIds = emptyList(),
    id = this.id.toInt(),
    originalLanguage = "",
    originalTitle = this.title,
    overview = this.overview,
    popularity = 0.0,
    posterPath = this.poster,
    releaseDate = this.releaseDate,
    title = "",
    video = false,
    voteAverage = 0.0,
)