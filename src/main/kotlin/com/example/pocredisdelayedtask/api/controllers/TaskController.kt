package com.example.pocredisdelayedtask.api.controllers

import com.example.pocredisdelayedtask.api.dtos.DelayedTaskDto
import com.example.pocredisdelayedtask.api.dtos.ExecutedTaskDto
import com.example.pocredisdelayedtask.business.usecases.tasks.FindAllTasksUseCase
import com.example.pocredisdelayedtask.business.usecases.tasks.ProduceDelayedTaskUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/delay-tasks")
class TaskController(private val produceDelayedTaskUseCase: ProduceDelayedTaskUseCase, private val findAllTasksUseCase: FindAllTasksUseCase) {

    @PostMapping("/queue")
    fun queue(@RequestBody delayTaskDto: DelayedTaskDto) {
        produceDelayedTaskUseCase.execute(delayTaskDto.toTaskBO())
    }

    @GetMapping("/executed")
    fun getExecuted(): List<ExecutedTaskDto> {
        return findAllTasksUseCase.execute().map { ExecutedTaskDto.fromTaskBO(it) }
    }
}
