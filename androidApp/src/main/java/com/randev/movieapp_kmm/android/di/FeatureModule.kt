package com.randev.movieapp_kmm.android.di

import com.randev.movieapp_kmm.android.presentation.MainViewModel
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val featureModule = module {
    single { MainViewModel(get()) }
}