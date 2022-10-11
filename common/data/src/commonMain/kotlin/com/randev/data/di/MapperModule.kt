package com.randev.data.di

import com.randev.data.mapper.MovieMapper
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

val mapperModule = module {
    single { MovieMapper() }
}