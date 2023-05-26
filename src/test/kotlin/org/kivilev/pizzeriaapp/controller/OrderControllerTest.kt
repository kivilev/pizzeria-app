/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.mapper.OrderDtoMapper
import org.kivilev.pizzeriaapp.exception.ObjectNotFoundException
import org.kivilev.pizzeriaapp.service.OrderService
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.UUID

@WebMvcTest(OrderController::class)
class OrderControllerTest {
    // TODO: Add other tests
    @MockBean
    private lateinit var orderService: OrderService

    @MockBean
    private lateinit var orderDtoMapper: OrderDtoMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Getting not existed order should return error`() {
        val orderId = UUID.randomUUID()
        `when`(orderService.getOrder(orderId)).thenThrow(ObjectNotFoundException::class.java)

        mockMvc.get("/api/v1/orders/$orderId")
            .andExpect {
                status { isNotFound() }
            }
    }
}
