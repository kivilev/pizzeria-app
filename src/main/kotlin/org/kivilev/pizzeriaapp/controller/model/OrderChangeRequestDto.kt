/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email

@Schema(description = "Data for changing order")
data class OrderChangeRequestDto(
    @field:Email
    @field:Schema(description = "Order email", required = false, example = "email@email.com", maxLength = 100)
    val email: String?,
    @field:Schema(description = "Order state", required = false, example = "DELIVERING")
    val state: OrderStateDto?
)
