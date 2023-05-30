package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime
import java.util.UUID

@Schema(description = "Order properties in response")
data class OrderResponseDto(
    @field:Schema(description = "Order Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    val id: UUID,
    @field:Schema(description = "Order state", required = true, example = "DELIVERING")
    val state: OrderStateDto,
    @field:Schema(description = "Order email", required = true, example = "email@email.com", maxLength = 100)
    val email: String,
    @field:Schema(description = "Order creation date time", required = true, example = "2023-05-26T06:56:08.7654548Z")
    val createdDateTime: ZonedDateTime,
    @field:Schema(description = "Customer Id", required = true, example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
    val customerId: UUID,
    @field:ArraySchema(schema = Schema(description = "List of toppings", allOf = [ToppingResponseDto::class]))
    val toppings: List<ToppingResponseDto>
)
