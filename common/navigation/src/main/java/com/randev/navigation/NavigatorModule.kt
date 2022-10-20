package com.randev.navigation

import org.koin.dsl.module


/**
 * @author Raihan Arman
 * @date 19/10/22
 */

val navigatorModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
}