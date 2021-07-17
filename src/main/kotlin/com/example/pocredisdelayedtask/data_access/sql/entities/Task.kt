package com.example.pocredisdelayedtask.data_access.sql.entities


import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Task(@field:GeneratedValue(strategy = GenerationType.IDENTITY) @field:Id var id: Long?, var startTime: LocalDateTime, var persistTime: LocalDateTime?, var description: String, var delay: Long)
