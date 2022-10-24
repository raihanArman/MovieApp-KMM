package com.randev.data.di

import com.randev.data.mapper.FavoriteMapper
import com.randev.data.mapper.MovieCreditsMapper
import com.randev.data.mapper.MovieDetailMapper
import com.randev.data.mapper.MovieMapper
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val mapperModule = module {
    single { MovieMapper() }
    single { MovieDetailMapper() }
    single { MovieCreditsMapper() }
    single { FavoriteMapper() }
}