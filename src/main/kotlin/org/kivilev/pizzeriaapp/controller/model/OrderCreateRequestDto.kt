package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import java.util.UUID

data class OrderCreateRequestDto(
    @field:Schema(description = "Customer Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    var customerId: UUID,

    @field:Email
    @field:Schema(description = "Order email", required = true, example = "email@email.com")
    var email: String,

    @field:ArraySchema(schema = Schema(description = "List of toppings UUID", allOf = [UUID::class]))
    var toppings: Set<UUID>
)
