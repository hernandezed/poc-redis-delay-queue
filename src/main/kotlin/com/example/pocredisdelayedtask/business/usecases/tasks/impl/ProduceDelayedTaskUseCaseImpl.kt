package com.example.pocredisdelayedtask.business.usecases.tasks.impl

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.business.usecases.tasks.ProduceDelayedTaskUseCase
import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import com.example.pocredisdelayedtask.data_access.sql.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class ProduceDelayedTaskUseCaseImpl(private val delayedTaskQueue: DelayedQueue<DelayedTask>, private val taskRepository: TaskRepository) : ProduceDelayedTaskUseCase {
    override fun execute(taskBO: TaskBO) {
        delayedTaskQueue.add(taskBO.toDelayTask(), taskBO.delayMillis)
    }
}
