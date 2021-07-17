package com.example.pocredisdelayedtask.business

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import com.example.pocredisdelayedtask.data_access.sql.repositories.TaskRepository
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DelayedTaskConsumer(private val delayedTaskQueue: DelayedQueue<DelayedTask>, private val taskRepository: TaskRepository) {
    @Scheduled(fixedDelay = 15000)
    @SchedulerLock(name = "delayTaskLock")
    fun consume() {
        val task = TaskBO.fromDelayedTask(delayedTaskQueue.first)?.toTask()
        if (task != null) {
            taskRepository.save(task)
        }
    }
}
