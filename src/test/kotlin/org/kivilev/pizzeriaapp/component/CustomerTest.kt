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
import org.kivilev.pizzeriaapp.controller.model.CustomerCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.CustomerResponseDto
import org.kivilev.pizzeriaapp.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerTest {
    // TODO: Add other tests

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `Creating customer with valid properties should create client`() {
        val customerCreateRequestDto =
            CustomerCreateRequestDto(fullName = FULL_NAME, phoneNumber = VALID_PHONE_NUMBER, email = VALID_EMAIL)

        val actualResponse = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerCreateRequestDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val actualCustomerDto = objectMapper.readValue(actualResponse, CustomerResponseDto::class.java)
        val actualCustomerId = actualCustomerDto.id

        val actualCustomerOptional = customerRepository.findById(actualCustomerId)
        assertTrue(actualCustomerOptional.isPresent)
        val actualCustomerDb = actualCustomerOptional.get()

        with(actualCustomerDto) {
            assertEquals(fullName, this.fullName)
            assertEquals(email, this.email)
            assertEquals(phoneNumber, this.phoneNumber)
        }
        with(actualCustomerDb) {
            assertEquals(fullName, this.fullName)
            assertEquals(email, this.email)
            assertEquals(phoneNumber, this.phoneNumber)
        }
    }

    @Test
    fun `Deleting not existed customer should get error`() {
        val customerId = UUID.randomUUID()

        val actualErrorMessage = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/v1/customers/$customerId")
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn().response.errorMessage

        assertEquals(OBJECT_NOT_FOUND_MESSAGE, actualErrorMessage)
    }

    private companion object {
        const val FULL_NAME = "full name"
        const val VALID_PHONE_NUMBER = "+995-222-222-222"
        const val VALID_EMAIL = "email@email.com"
        const val OBJECT_NOT_FOUND_MESSAGE = "Object not found"
    }
}
