package com.example.pocredisdelayedtask.api.dtos

import com.example.pocredisdelayedtask.business.entities.TaskBO

import java.time.LocalDateTime

data class ExecutedTaskDto(var id: Long, var startTime: LocalDateTime, var persistTime: LocalDateTime?, var description: String, val delayMillis: Long) {
    companion object {
        fun fromTaskBO(taskBO: TaskBO): ExecutedTaskDto {
            return ExecutedTaskDto(taskBO.id!!, taskBO.startTime, taskBO.persistTime, taskBO.description, taskBO.delayMillis)
        }
    }
}
