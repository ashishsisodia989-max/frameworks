package com.example.kmmsharedkit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform