/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class ApplicationConfig {

    @Bean
    fun clock(): Clock {
        return Clock.systemUTC()
    }
}