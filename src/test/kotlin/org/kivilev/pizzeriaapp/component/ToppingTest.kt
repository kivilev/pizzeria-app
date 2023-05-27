/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.component

import jakarta.transaction.Transactional
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.model.ToppingResponseDto
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.get

@Transactional
class ToppingTest : ComponentTestBase() {
    // TODO: Add other tests
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
