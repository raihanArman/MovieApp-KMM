package com.randev.database

import com.randev.movieapp_kmm.db.MovieDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 18/10/22
 */

fun setupForIos(): MovieDatabase {
    val driver = NativeSqliteDriver(MovieDatabase.Schema, "app.db")
    return MovieDatabase(driver)
}

actual fun databaseModule() = module {
    single {
        setupForIos()
    }
}