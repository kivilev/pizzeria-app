/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

class ToppingResponseDto(
    @field:Schema(description = "Topping Id", required = true)
    val id: UUID,
    @field:Schema(description = "Unique code", required = true)
    val code: String,
    @field:Schema(description = "Full name", required = true, pattern = "^[A-Z0-9_]+\$")
    val fullName: String
)
