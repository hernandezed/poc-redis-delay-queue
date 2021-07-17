package com.example.pocredisdelayedtask.business.usecases.tasks.impl

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.business.usecases.tasks.FindAllTasksUseCase
import com.example.pocredisdelayedtask.data_access.sql.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class FindAllTasksUseCaseImpl(private val taskRepository: TaskRepository) : FindAllTasksUseCase {
    override fun execute(): List<TaskBO> {
        return taskRepository.findAll().map { TaskBO.fromTask(it) }
    }


}
