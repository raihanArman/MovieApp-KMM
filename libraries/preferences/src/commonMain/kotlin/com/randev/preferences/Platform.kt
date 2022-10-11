package com.randev.preferences

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform