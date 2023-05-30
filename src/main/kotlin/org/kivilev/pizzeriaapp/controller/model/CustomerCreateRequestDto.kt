/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Schema(description = "Customer properties in creating request")
data class CustomerCreateRequestDto(
    @field:NotBlank
    @field:Schema(description = "Full name", required = true, example = "Customer full name")
    val fullName: String,

    @field:Schema(description = "Phone number", example = "+1-222-333-2222")
    val phoneNumber: String?,

    @field:Email
    @field:Schema(description = "Email", example = "email@email.com")
    val email: String?
)
