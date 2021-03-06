package com.example.pocredisdelayedtask.business.entities

import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.sql.entities.Task

import java.time.LocalDateTime

data class TaskBO(val id: Long?, val startTime: LocalDateTime, var persistTime: LocalDateTime?, val description: String, val delayMillis: Long) {

    companion object {
        fun fromDelayedTask(delayedTask: DelayedTask?): TaskBO? {
            return if (delayedTask != null) {
                TaskBO(null, delayedTask.startTime, null, delayedTask.description, delayedTask.delay)
            } else null;
        }

        fun fromTask(task: Task): TaskBO {
            return TaskBO(task.id, task.startTime, task.persistTime, task.description, task.delay)
        }
    }

    fun process() {
        this.persistTime = LocalDateTime.now()
    }

    fun toTask(): Task {
        return Task(null, startTime, persistTime, description, delayMillis)
    }

    fun toDelayTask(): DelayedTask {
        return DelayedTask(startTime, description, delayMillis)
    }

}
