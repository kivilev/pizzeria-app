/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.component

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.model.OrderCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.OrderResponseDto
import org.kivilev.pizzeriaapp.controller.model.OrderStateDto
import org.kivilev.pizzeriaapp.model.Customer
import org.kivilev.pizzeriaapp.model.Order
import org.kivilev.pizzeriaapp.model.OrderState
import org.kivilev.pizzeriaapp.model.Topping
import org.kivilev.pizzeriaapp.repository.CustomerRepository
import org.kivilev.pizzeriaapp.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.post
import java.time.Clock
import java.time.ZonedDateTime
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderTest {
    // TODO: Add other tests

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var clock: Clock

    @Test
    fun `Creating order with valid properties should create order`() {
        val customer = createCustomer()
        val customerId = customer.id!!
        val expectedSizeOfToppings = 2
        val expectedOrderStateDto = OrderStateDto.CREATED
        val expectedOrderState = OrderState.CREATED

        val orderCreateRequestDto = OrderCreateRequestDto(
            customerId,
            VALID_EMAIL,
            setOf(UUID.fromString(TOPPING_ID1), UUID.fromString(TOPPING_ID2))
        )

        val actualResponse = mockMvc.post("/api/v1/orders/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(orderCreateRequestDto)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString

        val actualOrderDto = objectMapper.readValue(actualResponse, OrderResponseDto::class.java)
        val actualOrderId = actualOrderDto.id

        val actualOrderOptional = orderRepository.findById(actualOrderId)
        assertTrue(actualOrderOptional.isPresent)
        val actualOrderDb = actualOrderOptional.get()

        with(actualOrderDto) {
            assertEquals(customerId, this.customerId)
            assertEquals(VALID_EMAIL, email)
            assertEquals(expectedOrderStateDto, state)
            assertEquals(expectedSizeOfToppings, toppings.size)
        }
        with(actualOrderDb) {
            assertEquals(customerId, this.customer.id)
            assertEquals(VALID_EMAIL, email)
            assertEquals(expectedOrderState, state)
            assertEquals(expectedSizeOfToppings, toppings.size)
        }
    }

    @Test
    fun `Creating order second time should recreate order`() {
        val firstOrder = createOrder(OrderState.CREATED)
        val expectedOrderId = firstOrder.id
        val customerId = firstOrder.customer.id!!
        val expectedSizeOfToppings = 1
        val expectedOrderStateDto = OrderStateDto.CREATED
        val expectedOrderState = OrderState.CREATED

        val secondOrderCreateRequestDto = OrderCreateRequestDto(
            firstOrder.customer.id!!,
            VALID_EMAIL2,
            setOf(UUID.fromString(TOPPING_ID1))
        )

        val actualResponse = mockMvc.post("/api/v1/orders/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(secondOrderCreateRequestDto)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString

        val actualOrderDto = objectMapper.readValue(actualResponse, OrderResponseDto::class.java)
        val actualOrderId = actualOrderDto.id

        assertEquals(expectedOrderId, actualOrderId)

        val actualOrderOptional = orderRepository.findById(actualOrderId)
        assertTrue(actualOrderOptional.isPresent)
        val actualOrderDb = actualOrderOptional.get()

        with(actualOrderDto) {
            assertEquals(customerId, customerId)
            assertEquals(VALID_EMAIL2, email)
            assertEquals(expectedOrderStateDto, state)
            assertEquals(expectedSizeOfToppings, toppings.size)
        }
        with(actualOrderDb) {
            assertEquals(customerId, this.customer.id)
            assertEquals(VALID_EMAIL2, email)
            assertEquals(expectedOrderState, state)
            assertEquals(expectedSizeOfToppings, toppings.size)
        }
    }

    @Test
    fun `Cancelling order in DELEVERED state should get error`() {
        val order = createOrder(OrderState.DELIVERED)

        val actualErrorMessage = mockMvc.delete("/api/v1/orders/${order.id}")
            .andExpect {
                status { isBadRequest() }
            }.andReturn().response.errorMessage

        assertEquals(INVALID_OBJECT_STATE_MESSAGE, actualErrorMessage)
    }

    private fun createCustomer(): Customer {
        val customer = Customer(fullName = FULL_NAME, phoneNumber = VALID_PHONE_NUMBER, email = VALID_EMAIL)
        return customerRepository.save(customer)
    }

    private fun createOrder(state: OrderState = OrderState.CREATED, toppings: List<Topping> = emptyList()): Order {
        val order = Order(
            email = VALID_EMAIL,
            state = state,
            createdDateTime = ZonedDateTime.now(clock),
            customer = createCustomer(),
            toppings = toppings
        )
        return orderRepository.save(order)
    }

    private companion object {
        const val FULL_NAME = "full name"
        const val VALID_PHONE_NUMBER = "+995-222-222-222"
        const val VALID_EMAIL = "email@email.com"
        const val VALID_EMAIL2 = "email2@email.com"
        const val TOPPING_ID1 = "7d1f29b7-4651-40b6-8042-e8e8e2345150"
        const val TOPPING_ID2 = "7d1f29b7-4651-40b6-8042-e8e8e2345152"
        const val INVALID_OBJECT_STATE_MESSAGE = "Object is not in appropriate state"
    }
}
