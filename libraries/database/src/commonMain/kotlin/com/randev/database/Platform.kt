package com.randev.database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform