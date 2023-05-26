/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.kivilev.pizzeriaapp.controller.mapper.OrderDtoMapper
import org.kivilev.pizzeriaapp.controller.model.OrderChangeRequestDto
import org.kivilev.pizzeriaapp.controller.model.OrderCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.OrderResponseDto
import org.kivilev.pizzeriaapp.controller.model.OrderStateDto
import org.kivilev.pizzeriaapp.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class OrderController(
    private val orderService: OrderService,
    private val orderDtoMapper: OrderDtoMapper
) {
    @PostMapping("/api/v1/orders/")
    @Operation(summary = "Create a new order")
    @ResponseStatus(HttpStatus.CREATED)
    fun addOrder(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order properties")
        @Valid
        @RequestBody orderCreateRequestDto: OrderCreateRequestDto
    ): OrderResponseDto {
        return orderDtoMapper.toDto(orderService.addOrder(orderCreateRequestDto.customerId, orderCreateRequestDto.email, orderCreateRequestDto.toppings))
    }

    @PatchMapping("/api/v1/orders/{id}/")
    @Operation(summary = "Change order properties")
    fun changeOrder(
        @Parameter(description = "Order UUID", example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
        @PathVariable("id") orderId: UUID,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order properties")
        @Valid
        @RequestBody orderChangeRequestDto: OrderChangeRequestDto
    ): OrderResponseDto {
        val state = orderChangeRequestDto.state?.let { orderDtoMapper.fromDto(it) }
        return orderDtoMapper.toDto(orderService.changeOrder(orderId, orderChangeRequestDto.email, state))
    }

    @DeleteMapping("/api/v1/orders/{id}")
    @Operation(summary = "Cancel order")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelTopping(
        @Parameter(description = "Order UUID", example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
        @org.hibernate.validator.constraints.UUID
        @PathVariable("id") orderId: UUID
    ) {
        orderService.cancelOrder(orderId)
    }

    @GetMapping("/api/v1/orders/{id}")
    @Operation(summary = "Get an order info")
    fun getOrder(
        @Parameter(description = "Order UUID", example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
        @org.hibernate.validator.constraints.UUID
        @PathVariable("id") orderId: UUID
    ): OrderResponseDto {
        return orderDtoMapper.toDto(orderService.getOrder(orderId))
    }

    @GetMapping("/api/v1/customers/{id}/orders/")
    @Operation(summary = "Get customer orders")
    fun getOrdersByCustomer(
        @Parameter(description = "Customer UUID", example = "e4d15d95-3521-441e-bafa-2f3fe279c1a0")
        @org.hibernate.validator.constraints.UUID
        @PathVariable("id") customerId: UUID,
        @Parameter(description = "List of drone states")
        @RequestParam(value = "state") stateDtos: Set<OrderStateDto>
    ): List<OrderResponseDto> {
        // TODO: add pagination
        val states = orderDtoMapper.fromDto(stateDtos)
        return orderDtoMapper.toDto(orderService.getOrders(customerId, states))
    }
}
