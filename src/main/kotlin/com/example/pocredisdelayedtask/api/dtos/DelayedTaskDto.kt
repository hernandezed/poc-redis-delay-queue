package com.example.pocredisdelayedtask.api.dtos

import com.example.pocredisdelayedtask.business.entities.TaskBO
import java.time.LocalDateTime

data class DelayedTaskDto(var description: String, val delayMillis: Long) {
    fun toTaskBO(): TaskBO {
        return TaskBO(null, LocalDateTime.now(), null, description, delayMillis)
    }
}
