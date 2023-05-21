/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerCreateRequestDto(
    @field:NotBlank
    @field:Schema(description = "Full name", required = true)
    var fullName: String,

    @field:Schema(description = "Phone number")
    var phoneNumber: String?,

    @field:Email
    @field:Schema(description = "Email")
    var email: String?
)
