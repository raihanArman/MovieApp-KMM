package com.randev.data.di

import com.randev.data.datasource.local.FavoriteDataSource
import com.randev.data.datasource.local.FavoriteDataSourceImpl
import com.randev.data.datasource.local.MovieDataSource
import com.randev.data.datasource.local.MovieDataSourceImpl
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 18/10/22
 */

val dataSourceModule = module {
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<FavoriteDataSource> { FavoriteDataSourceImpl(get()) }
}