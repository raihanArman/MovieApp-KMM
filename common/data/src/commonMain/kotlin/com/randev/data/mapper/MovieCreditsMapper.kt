package com.randev.data.mapper

import com.randev.core.arch.BaseMapper
import com.randev.data.response.movie_credits.MovieCreditsResponse
import com.randev.domain.model.movie_credits.CastModel
import com.randev.domain.model.movie_credits.MovieCreditsModel

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

class MovieCreditsMapper: BaseMapper<MovieCreditsResponse, MovieCreditsModel>() {
    override fun map(value: MovieCreditsResponse): MovieCreditsModel {
        return value.mapToModel()
    }
}

fun MovieCreditsResponse?.mapToModel() = MovieCreditsModel(
    castList = this?.cast?.map {
        it.mapToModel()
    } ?: emptyList()
)

fun MovieCreditsResponse.Cast?.mapToModel() = CastModel(
    castId = this?.castId ?: 0,
    character = this?.character.orEmpty(),
    creditId = this?.creditId.orEmpty(),
    gender = this?.gender ?: 0,
    id = this?.id ?: 0,
    name = this?.name.orEmpty(),
    originalName = this?.originalName.orEmpty(),
    popularity = this?.popularity ?: 0.0,
    profilePath = this?.profilePath.orEmpty()
)