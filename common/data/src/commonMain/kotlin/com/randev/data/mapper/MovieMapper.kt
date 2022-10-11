package com.randev.data.mapper

import com.randev.core.arch.BaseMapper
import com.randev.data.response.MovieResponse
import com.randev.data.response.MovieResultResponse
import com.randev.domain.model.DataMovieModel
import com.randev.domain.model.MovieModel

/**
 * @author Raihan Arman
 * @date 11/10/22
 */
class MovieMapper: BaseMapper<MovieResponse, MovieModel>() {
    override fun map(value: MovieResponse): MovieModel {
        return MovieModel(
            page = value.page,
            results = value.results.map {
                DataMovieModel(
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                )
            },
            totalPages = value.totalPages,
            totalResults = value.totalResults
        )
    }
}