/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Topping properties in response")
data class ToppingResponseDto(
    @field:Schema(description = "Topping Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    val id: UUID,
    @field:Schema(description = "Unique code", required = true, pattern = "^[A-Z0-9_]+\$", example = "CODE_TOPPING_1", maxLength = 100)
    val code: String,
    @field:Schema(description = "Full name", required = true, example = "Topping full name", maxLength = 1000)
    val fullName: String
)
