package com.example.pocredisdelayedtask.business.usecases.tasks

import com.example.pocredisdelayedtask.business.entities.TaskBO

interface FindAllTasksUseCase {
    fun execute(): List<TaskBO>
}
