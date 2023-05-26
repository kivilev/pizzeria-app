/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.mapper

import org.kivilev.pizzeriaapp.controller.model.OrderResponseDto
import org.kivilev.pizzeriaapp.controller.model.OrderStateDto
import org.kivilev.pizzeriaapp.model.Order
import org.kivilev.pizzeriaapp.model.OrderState
import org.springframework.stereotype.Component

@Component
class OrderDtoMapper(private val toppingMapper: ToppingMapper) {
    fun toDto(order: Order): OrderResponseDto =
        with(order) {
            OrderResponseDto(
                id!!,
                toDto(state),
                email,
                createdDateTime,
                customer.id!!,
                toppingMapper.toDto(toppings)
            )
        }

    fun toDto(orders: List<Order>): List<OrderResponseDto> =
        orders.map { order -> toDto(order) }

    fun fromDto(orderStateDto: OrderStateDto): OrderState =
        when (orderStateDto) {
            OrderStateDto.CREATED -> OrderState.CREATED
            OrderStateDto.DELIVERING -> OrderState.DELIVERING
            OrderStateDto.DELIVERED -> OrderState.DELIVERED
            OrderStateDto.CANCELLED -> OrderState.CANCELLED
        }

    fun fromDto(orderStatesDto: Set<OrderStateDto>): List<OrderState> =
        orderStatesDto.map { orderStateDto -> fromDto(orderStateDto) }

    private fun toDto(orderState: OrderState): OrderStateDto =
        when (orderState) {
            OrderState.CREATED -> OrderStateDto.CREATED
            OrderState.DELIVERING -> OrderStateDto.DELIVERING
            OrderState.DELIVERED -> OrderStateDto.DELIVERED
            OrderState.CANCELLED -> OrderStateDto.CANCELLED
        }
}
