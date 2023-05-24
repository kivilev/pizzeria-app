/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ToppingCreateRequestDto(
    @field:Pattern(regexp = "^[A-Z0-9_]+\$")
    @field:Schema(description = "Unique code", required = true, pattern = "^[A-Z0-9_]+\$")
    var code: String,

    @field:NotBlank
    @field:Schema(description = "Full name", required = true)
    var fullName: String
)
