package com.randev.data.di

import com.randev.data.datasource.remote.MovieApi
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val clientModule = module {
    single { MovieApi(get()) }
}