package com.randev.movieapp_kmm

import com.randev.data.di.clientModule
import com.randev.data.di.dataSourceModule
import com.randev.data.di.mapperModule
import com.randev.data.di.repositoryModule
import com.randev.database.databaseModule
import com.randev.domain.di.useCaseModule
import com.randev.network.ktorEngineModule
import com.randev.network.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        databaseModule() +
        ktorEngineModule() +
        dataSourceModule +
        networkModule +
        clientModule +
        mapperModule +
        repositoryModule +
        useCaseModule
    )
}

fun initKoin() = initKoin {}