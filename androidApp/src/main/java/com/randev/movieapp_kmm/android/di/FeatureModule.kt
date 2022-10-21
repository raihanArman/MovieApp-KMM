package com.randev.movieapp_kmm.android.di

import com.randev.movieapp_kmm.android.presentation.detail.DetailViewModel
import com.randev.movieapp_kmm.android.presentation.home.HomeViewModel
import com.randev.movieapp_kmm.android.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val featureModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { params ->
        DetailViewModel(
            appNavigator = get(),
            stateHandle = params.get(),
            detailUseCase = get(),
            creditsUseCase = get()
        )
    }
}