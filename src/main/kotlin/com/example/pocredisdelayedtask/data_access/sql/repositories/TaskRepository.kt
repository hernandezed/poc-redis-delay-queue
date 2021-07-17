package com.example.pocredisdelayedtask.data_access.sql.repositories

import com.example.pocredisdelayedtask.data_access.sql.entities.Task
import org.springframework.data.jpa.repository.JpaRepository



interface TaskRepository : JpaRepository<Task, Long>
