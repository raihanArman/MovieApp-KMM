package com.randev.movieapp_kmm.android.application

import android.app.Application
import com.randev.movieapp_kmm.android.di.featureModule
import com.randev.movieapp_kmm.initKoin
import com.randev.navigation.navigatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * @author Raihan Arman
 * @date 11/10/22
 */



class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieApplication)
            modules(
                navigatorModule,
                featureModule,
            )
        }
    }
}