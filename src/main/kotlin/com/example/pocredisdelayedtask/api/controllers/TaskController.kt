package com.example.pocredisdelayedtask.api.controllers

import com.example.pocredisdelayedtask.api.dtos.DelayedTaskDto
import com.example.pocredisdelayedtask.api.dtos.ExecutedTaskDto
import com.example.pocredisdelayedtask.business.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/delay-tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping("/queue")
    fun queue(@RequestBody delayTaskDto: DelayedTaskDto) {
        taskService.produce(delayTaskDto.toTaskBO())
    }

    @GetMapping("/executed")
    fun getExecuted(): List<ExecutedTaskDto> {
        return taskService.getTasks().map { ExecutedTaskDto.fromTaskBO(it) }
    }
}
