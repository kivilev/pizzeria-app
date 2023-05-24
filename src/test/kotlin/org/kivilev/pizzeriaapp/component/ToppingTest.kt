/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.component

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.model.ToppingResponseDto
import org.kivilev.pizzeriaapp.repository.ToppingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ToppingTest {
    // TODO: Add other tests
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var toppingRepository: ToppingRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `Getting all toppings should return list of toppings`() {
        val expectedSize = 2

        mockMvc.get("/api/v1/toppings/") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(APPLICATION_JSON) }
            jsonPath("*", hasSize<ToppingResponseDto>(expectedSize))
        }
    }
}
