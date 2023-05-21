/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.controller.mapper.ToppingMapper
import org.kivilev.pizzeriaapp.controller.model.ToppingCreateRequestDto
import org.kivilev.pizzeriaapp.exception.ObjectNotFoundException
import org.kivilev.pizzeriaapp.service.ToppingService
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.UUID

@WebMvcTest(ToppingController::class)
class ToppingControllerTest {
    // TODO: Add other tests
    @MockBean
    private lateinit var toppingService: ToppingService

    @MockBean
    private lateinit var toppingMapper: ToppingMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Creating topping with invalid code should return error`() {
        val dto = ToppingCreateRequestDto(INVALID_CODE, FULL_NAME)

        mockMvc.post("/api/v1/toppings/") {
            contentType = MediaType.APPLICATION_JSON
            content = OBJECT_MAPPER.writeValueAsString(dto)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `Getting not existed topping should return error`() {
        val toppingID = UUID.randomUUID()
        `when`(toppingService.getTopping(toppingID)).thenThrow(ObjectNotFoundException::class.java)

        mockMvc.get("/api/v1/toppings/$toppingID")
            .andExpect {
                status { isNotFound() }
            }
    }

    private companion object {
        const val VALID_CODE = "CODE_1"
        const val INVALID_CODE = "code_1"
        const val FULL_NAME = "full name"
        val OBJECT_MAPPER = ObjectMapper()
    }
}
