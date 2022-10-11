package com.randev.network

import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

actual fun ktorEngineModule() = module {
    single { Darwin.create() }
}