package com.randev.data.di

import com.randev.data.repository.MovieDetailRepositoryImpl
import com.randev.data.repository.MovieRepositoryImpl
import com.randev.domain.repository.MovieDetailRepository
import com.randev.domain.repository.MovieRepository
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get(), get()) }
}