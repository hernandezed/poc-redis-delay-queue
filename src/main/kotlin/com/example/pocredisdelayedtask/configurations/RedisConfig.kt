package com.example.pocredisdelayedtask.configurations

import com.example.pocredisdelayedtask.data_access.redis.entities.DelayedTask
import com.example.pocredisdelayedtask.data_access.redis.structures.DelayedQueue
import com.example.pocredisdelayedtask.data_access.redis.structures.impl.DelayedQueueImpl
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class RedisConfig {
    @Bean
    fun delayedTaskQueue(connectionFactory: LettuceConnectionFactory): DelayedQueue<DelayedTask> {
        val template = RedisTemplate<String, DelayedTask>()
        template.setConnectionFactory(connectionFactory)
        template.afterPropertiesSet()
        return DelayedQueueImpl(template, "delayed-task");
    }

    @Bean
    fun lettuceClientConfigurationBuilderCustomizer(): LettuceClientConfigurationBuilderCustomizer? {
        return LettuceClientConfigurationBuilderCustomizer { clientConfigurationBuilder: LettuceClientConfigurationBuilder ->
            if (clientConfigurationBuilder.build().isUseSsl) {
                clientConfigurationBuilder.useSsl().disablePeerVerification()
            }
        }
    }
}
