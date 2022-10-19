package com.randev.movieapp_kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform