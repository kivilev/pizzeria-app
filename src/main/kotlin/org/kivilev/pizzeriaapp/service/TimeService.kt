/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.service

import org.springframework.stereotype.Component
import java.time.Clock
import java.time.ZonedDateTime

@Component
class TimeService(private val clock: Clock) {
    fun now(): ZonedDateTime = ZonedDateTime.now(clock)
}
