/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import org.junit.jupiter.api.Test
import org.kivilev.pizzeriaapp.service.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(ReportController::class)
class ReportControllerTest {
    // TODO: Add other tests
    @MockBean
    private lateinit var reportService: ReportService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Getting not existed report should return error`() {
        mockMvc.get("/api/v1/reports/?type=DUMMY_REPORT")
            .andExpect {
                status { isBadRequest() }
            }
    }
}
