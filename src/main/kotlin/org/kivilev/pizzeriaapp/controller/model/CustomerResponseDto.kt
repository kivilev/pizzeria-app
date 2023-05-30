/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Customer properties in response")
data class CustomerResponseDto(
    @field:Schema(description = "Customer Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    val id: UUID,
    @field:Schema(description = "Full name", required = true, example = "Customer full name", minLength = 5, maxLength = 1000)
    val fullName: String,
    @field:Schema(description = "Phone number", required = false, example = "+1-222-333-2222", minLength = 8, maxLength = 30)
    val phoneNumber: String?,
    @field:Schema(description = "Email", required = false, example = "email@email.com", maxLength = 100)
    val email: String?
)
