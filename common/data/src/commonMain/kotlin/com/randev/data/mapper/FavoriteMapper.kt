package com.randev.data.mapper

import com.randev.core.arch.BaseMapper
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieappkmm.db.FavoriteEntity

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class FavoriteMapper: BaseMapper<FavoriteEntity, FavoriteModel>() {
    override fun map(value: FavoriteEntity): FavoriteModel {
        return FavoriteModel(
            id = value.id.toInt(),
            title = value.title,
            poster = value.poster,
            releaseDate = value.releaseDate
        )
    }
}