package com.randev.data.mapper

import com.randev.core.arch.BaseMapper
import com.randev.data.response.movie_details.MovieDetailResponse
import com.randev.domain.model.movie_detail.GenreModel
import com.randev.domain.model.movie_detail.ImageModel
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.model.movie_detail.VideoModel

/**
 * @author Raihan Arman
 * @date 20/10/22
 */

class MovieDetailMapper: BaseMapper<MovieDetailResponse, MovieDetailModel>() {
    override fun map(value: MovieDetailResponse): MovieDetailModel {
        return value.mapToModel()
    }
}

fun MovieDetailResponse.mapToModel() = MovieDetailModel(
    adult = this.adult ?: false,
    backdropPath = this.backdropPath.orEmpty(),
    budget = this.budget ?: 0,
    genres = this.genres?.map {
        it?.mapToModel()
    } ?: emptyList(),
    id = this.id ?: 0,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity ?: 0.0,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    status = this.status.orEmpty(),
    title = this.title.orEmpty(),
    voteAverage = this.voteAverage ?: 0.0,
    voteCount = this.voteCount ?: 0,
    images = this.images?.backdrops?.map {
        it?.mapToModel()
    } ?: emptyList(),
    videos = this.videos?.results?.map {
        it?.mapToModel()
    } ?: emptyList()
)

fun MovieDetailResponse.Genre.mapToModel() = GenreModel(
    id = this.id ?: 0,
    name = this.name.orEmpty()
)

fun MovieDetailResponse.Images.Backdrop?.mapToModel() = ImageModel(
    url = this?.filePath.orEmpty()
)

fun MovieDetailResponse.Videos.Result?.mapToModel() = VideoModel(
    id = this?.id.orEmpty(),
    key = this?.key.orEmpty()
)