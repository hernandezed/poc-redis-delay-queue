package com.example.pocredisdelayedtask.data_access.redis.structures.impl

import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

class DelayedQueueImpl<T>(val redisTemplate: RedisTemplate<String, T>, private val queueName: String) : DelayedQueue<T> {
    override fun add(delayedTask: T, delayMillis: Long) {
        redisTemplate.opsForZSet().add(queueName, delayedTask, LocalDateTime.now().plus(delayMillis, ChronoUnit.MILLIS).toEpochSecond(ZoneOffset.UTC).toDouble() * 0.0000000001)
    }

    override val first: T?
        get() {
            val iter: Iterator<T?> = redisTemplate.opsForZSet().reverseRangeByScore(queueName,
                    Long.MIN_VALUE.toDouble(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toDouble() * 0.0000000001, 0,
                    1)!!.iterator()
            return if (iter.hasNext()) {
                var value = iter.next()
                redisTemplate.opsForZSet().remove(queueName, value)
                value
            } else null
        }
}

