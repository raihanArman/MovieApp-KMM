package com.randev.data.di

import com.randev.data.repository.MovieDetailRepositoryImpl
import com.randev.data.repository.MovieRepositoryImpl
import com.randev.data.repository.MovieSearchRepositoryImpl
import com.randev.domain.repository.MovieDetailRepository
import com.randev.domain.repository.MovieRepository
import com.randev.domain.repository.MovieSearchRepository
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get(), get(), get()) }
    single<MovieSearchRepository> { MovieSearchRepositoryImpl(get(), get()) }
}