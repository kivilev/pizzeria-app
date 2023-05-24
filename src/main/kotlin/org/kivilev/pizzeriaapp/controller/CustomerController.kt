/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.kivilev.pizzeriaapp.controller.mapper.CustomerMapper
import org.kivilev.pizzeriaapp.controller.model.CustomerCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.CustomerResponseDto
import org.kivilev.pizzeriaapp.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class CustomerController(
    private val customerService: CustomerService,
    private val customerMapper: CustomerMapper
) {
    @PostMapping("/api/v1/customers/")
    @Operation(summary = "Create a new one customer")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer properties")
        @Valid
        @RequestBody customerCreateRequestDto: CustomerCreateRequestDto
    ): CustomerResponseDto {
        val newCustomer = customerMapper.fromDto(customerCreateRequestDto)
        return customerMapper.toDto(customerService.addCustomer(newCustomer))
    }

    @GetMapping("/api/v1/customers/")
    @Operation(summary = "Get list of customers")
    fun getCustomers(): List<CustomerResponseDto> {
        // TODO: add pagination
        return customerMapper.toDto(customerService.getCustomers())
    }

    @GetMapping("/api/v1/customers/{id}")
    @Operation(summary = "Get a customer info")
    fun getCustomer(
        @Parameter(description = "Customer UUID")
        @org.hibernate.validator.constraints.UUID
        @PathVariable("id") customerId: UUID
    ): CustomerResponseDto {
        return customerMapper.toDto(customerService.getCustomer(customerId))
    }

    @DeleteMapping("/api/v1/customers/{id}")
    @Operation(summary = "Delete a customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(
        @Parameter(description = "Customer UUID")
        @org.hibernate.validator.constraints.UUID
        @PathVariable("id") customerId: UUID
    ) {
        customerService.removeCustomer(customerId)
    }
}
