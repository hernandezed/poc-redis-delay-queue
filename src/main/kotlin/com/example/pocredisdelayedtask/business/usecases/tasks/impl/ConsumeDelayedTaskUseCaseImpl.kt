package com.example.pocredisdelayedtask.business.usecases.tasks.impl

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.business.usecases.tasks.ConsumeDelayedTaskUseCase
import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import com.example.pocredisdelayedtask.data_access.sql.repositories.TaskRepository
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ConsumeDelayedTaskUseCaseImpl(private val delayedTaskQueue: DelayedQueue<DelayedTask>,
                                    private val taskRepository: TaskRepository) : ConsumeDelayedTaskUseCase {
    @Scheduled(fixedDelay = 10000)
    @SchedulerLock(name = "delayTaskLock")
    override fun execute() {
        val task = TaskBO.fromDelayedTask(delayedTaskQueue.first)
        task?.let {
            it.process()
            taskRepository.save(it.toTask())
        }
    }
}
