package com.example.pocredisdelayedtask.data_access.redis.structures

interface DelayedQueue<T> {
    fun add(delayedTask: T, delayMillis: Long)
    val first: T?
}
