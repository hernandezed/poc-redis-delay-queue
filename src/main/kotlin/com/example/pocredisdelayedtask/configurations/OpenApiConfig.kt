package com.example.pocredisdelayedtask.configurations

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun creditPositionApi(): OpenAPI = OpenAPI()
            .info(Info().title("Delayed Task Api"))
}
