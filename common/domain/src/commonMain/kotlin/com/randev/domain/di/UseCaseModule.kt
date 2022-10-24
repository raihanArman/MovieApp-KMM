package com.randev.domain.di

import com.randev.domain.usecase.DeleteFavoriteUseCase
import com.randev.domain.usecase.GetFavoriteUseCase
import com.randev.domain.usecase.GetMovieCreditsUseCase
import com.randev.domain.usecase.GetMovieDetailUseCase
import com.randev.domain.usecase.GetMovieUpcomingUseCase
import com.randev.domain.usecase.GetMovieUseCase
import com.randev.domain.usecase.InsertFavoriteUseCase
import com.randev.domain.usecase.SearchMovieUseCase
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val useCaseModule = module {
    single { GetMovieUseCase(get()) }
    single { GetMovieDetailUseCase(get()) }
    single { GetMovieCreditsUseCase(get()) }
    single { GetMovieUpcomingUseCase(get()) }
    single { SearchMovieUseCase(get()) }
    single { GetFavoriteUseCase(get()) }
    single { InsertFavoriteUseCase(get()) }
    single { DeleteFavoriteUseCase(get()) }
}