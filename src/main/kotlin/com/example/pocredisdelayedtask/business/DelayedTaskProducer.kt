package com.example.pocredisdelayedtask.business

import com.example.pocredisdelayedtask.business.entities.TaskBO
import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import org.springframework.stereotype.Component

@Component
class DelayedTaskProducer(val delayedTaskQueue: DelayedQueue<DelayedTask>) {
    fun produce(taskBO: TaskBO) {
        delayedTaskQueue.add(taskBO.toDelayTask(), taskBO.delayMillis)
    }
}
