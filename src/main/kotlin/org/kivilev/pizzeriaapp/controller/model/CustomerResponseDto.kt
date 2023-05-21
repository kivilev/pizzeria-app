/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class CustomerResponseDto(
    @field:Schema(description = "Customer Id", required = true)
    val id: UUID,
    @field:Schema(description = "Full name", required = true)
    val fullName: String,
    @field:Schema(description = "Phone number", required = false)
    val phoneNumber: String?,
    @field:Schema(description = "Email", required = false)
    val email: String?
)
