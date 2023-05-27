/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class CustomerResponseDto(
    @field:Schema(description = "Customer Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    val id: UUID,
    @field:Schema(description = "Full name", required = true, example = "Customer full name")
    val fullName: String,
    @field:Schema(description = "Phone number", required = false, example = "+1-222-333-2222")
    val phoneNumber: String?,
    @field:Schema(description = "Email", required = false, example = "email@email.com")
    val email: String?
)
