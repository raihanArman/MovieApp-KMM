package com.randev.database

import com.randev.movieapp_kmm.db.MovieDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 18/10/22
 */

actual fun databaseModule() = module {
    single {
        val driver = AndroidSqliteDriver(MovieDatabase.Schema, context = get(), "app.db")
        MovieDatabase(driver)
    }
}