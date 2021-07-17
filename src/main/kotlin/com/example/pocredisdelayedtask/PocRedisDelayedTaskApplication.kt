package com.example.pocredisdelayedtask

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableRedisRepositories
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "1m")
class PocRedisDelayedTaskApplication

fun main(args: Array<String>) {
    runApplication<PocRedisDelayedTaskApplication>(*args)
}
