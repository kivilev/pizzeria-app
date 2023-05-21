/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.mapper.CustomerMapper
import org.kivilev.pizzeriaapp.controller.model.CustomerCreateRequestDto
import org.kivilev.pizzeriaapp.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(CustomerController::class)
class CustomerControllerTest {
    // TODO: Add other tests
    @MockBean
    private lateinit var customerService: CustomerService

    @MockBean
    private lateinit var customerMapper: CustomerMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Creating customer with invalid email should get error`() {
        val dto = CustomerCreateRequestDto(FULL_NAME, VALID_PHONE_NUMBER, INVALID_EMAIL)

        val actualErrorMessage = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(dto))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().response.errorMessage

        assertNotNull(actualErrorMessage)
        assertEquals("Invalid request content.", actualErrorMessage)
    }

    private companion object {
        const val INVALID_EMAIL = "sdfsdf111"
        const val FULL_NAME = "full name"
        const val VALID_PHONE_NUMBER = "+995-222-222-222"
        val OBJECT_MAPPER = ObjectMapper()
    }
}
