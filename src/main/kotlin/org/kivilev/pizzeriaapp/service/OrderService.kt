/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.service

import jakarta.transaction.Transactional
import org.kivilev.pizzeriaapp.exception.InvalidObjectStateException
import org.kivilev.pizzeriaapp.exception.ObjectNotFoundException
import org.kivilev.pizzeriaapp.model.Order
import org.kivilev.pizzeriaapp.model.OrderState
import org.kivilev.pizzeriaapp.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrDefault

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val customerService: CustomerService,
    private val toppingService: ToppingService,
    private val timeService: TimeService
) {
    @Transactional
    fun addOrder(customerId: UUID, email: String, toppingIds: Set<UUID>): Order {
        val customer = customerService.getCustomer(customerId)

        val toppings = toppingService.findToppingsByIds(toppingIds)
        if (toppings.size != toppingIds.size) {
            throw ObjectNotFoundException("Some of the toppings were not found")
        }

        val existedCreatedOrderOptional = orderRepository.findOrderByCustomer_IdAndState(customerId, OrderState.CREATED)
        existedCreatedOrderOptional.ifPresent {
            it.email = email
            it.customer = customer
            it.toppings = toppings
            it.createdDateTime = timeService.now()
        }
        val order = existedCreatedOrderOptional.getOrDefault(
            Order(
                email = email,
                customer = customer,
                toppings = toppings,
                createdDateTime = timeService.now()
            )
        )

        return orderRepository.save(order)
    }

    @Transactional
    fun changeOrder(orderId: UUID, email: String?, state: OrderState?): Order {
        val order = getOrder(orderId)
        var isChanged = false

        state?.let {
            checkOrderStateAndThrowIfNotValid(order.id, order.state, listOf(OrderState.CREATED, OrderState.DELIVERING))
            order.state = it
            isChanged = true
        }

        email?.let {
            checkOrderStateAndThrowIfNotValid(order.id, order.state, listOf(OrderState.CREATED))
            order.email = it
            isChanged = true
        }

        return if (isChanged) orderRepository.save(order) else order
    }

    @Transactional
    fun cancelOrder(orderId: UUID) {
        val order = getOrder(orderId)

        checkOrderStateAndThrowIfNotValid(order.id, order.state, listOf(OrderState.CREATED, OrderState.DELIVERING))

        order.state = OrderState.CANCELLED
        orderRepository.save(order)
    }

    @Transactional
    fun getOrder(orderId: UUID) =
        orderRepository.findOrderById(orderId)
            .orElseThrow { ObjectNotFoundException("Order with $orderId not found") }

    @Transactional
    fun getOrders(customerId: UUID, stateDto: List<OrderState>): List<Order> {
        return orderRepository.findAllByCustomer_IdAndStateIn(customerId, stateDto)
    }

    private fun checkOrderStateAndThrowIfNotValid(orderId: UUID?, state: OrderState, validSates: List<OrderState>) {
        if (state !in validSates) {
            throw InvalidObjectStateException("Order with $orderId is not in appropriate state $state")
        }
    }
}
