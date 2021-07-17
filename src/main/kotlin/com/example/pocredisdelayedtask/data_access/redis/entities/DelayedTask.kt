package com.example.pocredisdelayedtask.data_access.redis.entities

import java.io.Serializable
import java.time.LocalDateTime

data class DelayedTask(val startTime: LocalDateTime, val description: String, val delay: Long) : Serializable {
    companion object {
        private const val serialVersionUID = 9330938294L
    }
}
