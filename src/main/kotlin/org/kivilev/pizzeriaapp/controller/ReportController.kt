/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import io.swagger.v3.oas.annotations.Parameter
import org.kivilev.pizzeriaapp.controller.model.ReportTypeDto
import org.kivilev.pizzeriaapp.service.ReportService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportController(
    private val reportService: ReportService
) {

    @GetMapping("/api/v1/reports/")
    fun getToppingsUniqueCustomersReport(
        @Parameter(description = "Report type", example = "TOPPINGS_UNIQUE_CUSTOMERS_REPORT")
        @RequestParam("type") reportType: ReportTypeDto
    ): ResponseEntity<*> =
        when (reportType) {
            ReportTypeDto.TOPPINGS_UNIQUE_CUSTOMERS_REPORT -> ResponseEntity(reportService.getToppingsUniqueCustomersReport(), HttpStatus.OK)
        }
}
