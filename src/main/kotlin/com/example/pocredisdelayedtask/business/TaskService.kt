package com.example.pocredisdelayedtask.business

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.data_access.sql.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository, private val delayedTaskProducer: DelayedTaskProducer) {
    fun getTasks(): List<TaskBO> {
        return taskRepository.findAll().map { TaskBO.fromTask(it) }
    }

    fun produce(taskBO: TaskBO) {
        delayedTaskProducer.produce(taskBO)
    }
}
