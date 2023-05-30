/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.component

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.model.Customer
import org.kivilev.pizzeriaapp.model.Order
import org.kivilev.pizzeriaapp.model.OrderState
import org.kivilev.pizzeriaapp.model.Topping
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get

@Transactional
class ReportTest : ComponentTestBase() {
    // TODO: Add other tests
    @Test
    fun `Getting toppings unique customers report should return correct results`() {
        val toppings = getToppings()
        createOrder(state = OrderState.CREATED, toppings = listOf(toppings[0], toppings[1]))
        createOrder(state = OrderState.CREATED, toppings = listOf(toppings[0]))
        createOrder(state = OrderState.DELIVERED, toppings = listOf(toppings[0]))

        mockMvc.get("/api/v1/reports?type=TOPPINGS_UNIQUE_CUSTOMERS_REPORT") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { objectMapper.writeValueAsString(VALID_TOPPINGS_UNIQUE_CUSTOMERS_REPORT_RESULT) }
        }
    }

    private fun getToppings() =
        toppingRepository.findAll()

    private fun createCustomer(): Customer {
        val customer = Customer(fullName = FULL_NAME, phoneNumber = VALID_PHONE_NUMBER, email = VALID_EMAIL)
        return customerRepository.save(customer)
    }

    private fun createOrder(state: OrderState = OrderState.CREATED, toppings: List<Topping> = emptyList()): Order {
        val order = Order(
            email = VALID_EMAIL,
            state = state,
            createdDateTime = timeService.now(),
            customer = createCustomer(),
            toppings = toppings
        )
        return orderRepository.save(order)
    }

    private companion object {
        const val FULL_NAME = "full name"
        const val VALID_PHONE_NUMBER = "+995-222-222-222"
        const val VALID_EMAIL = "email@email.com"
        const val VALID_TOPPINGS_UNIQUE_CUSTOMERS_REPORT_RESULT = """
           [
              {
                "id": "7d1f29b7-4651-40b6-8042-e8e8e2345150",
                "fullName": "TOPPING1",
                "code": "CODE1",
                "uniqueCustomersCount": 2
              },
              {
                "id": "7d1f29b7-4651-40b6-8042-e8e8e2345152",
                "fullName": "TOPPING2",
                "code": "CODE2",
                "uniqueCustomersCount": 1
              }
            ]
        """
    }
}
