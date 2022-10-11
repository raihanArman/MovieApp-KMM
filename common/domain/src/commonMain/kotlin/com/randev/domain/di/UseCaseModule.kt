package com.randev.domain.di

import com.randev.domain.usecase.GetMovieUseCase
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val useCaseModule = module {
    single { GetMovieUseCase(get()) }
}