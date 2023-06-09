/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@Schema(description = "Topping properties in creating request")
data class ToppingCreateRequestDto(
    @field:Pattern(regexp = "^[A-Z0-9_]+\$")
    @field:Schema(description = "Unique code", required = true, pattern = "^[A-Z0-9_]+\$", example = "CODE_TOPPING_1", maxLength = 100)
    val code: String,

    @field:NotBlank
    @field:Schema(description = "Full name", required = true, example = "Topping full name", maxLength = 1000)
    val fullName: String
)
