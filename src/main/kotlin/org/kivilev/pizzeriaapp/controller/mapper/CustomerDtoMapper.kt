/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.mapper

import org.kivilev.pizzeriaapp.controller.model.CustomerCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.CustomerResponseDto
import org.kivilev.pizzeriaapp.model.Customer
import org.springframework.stereotype.Component

@Component
class CustomerDtoMapper {
    fun fromDto(customerCreateRequestDto: CustomerCreateRequestDto): Customer =
        Customer(
            null,
            customerCreateRequestDto.fullName,
            customerCreateRequestDto.email,
            customerCreateRequestDto.phoneNumber
        )

    fun toDto(customer: Customer): CustomerResponseDto =
        CustomerResponseDto(
            customer.id!!,
            customer.fullName,
            customer.phoneNumber,
            customer.email
        )

    fun toDto(customers: List<Customer>): List<CustomerResponseDto> =
        customers.map { customer -> toDto(customer) }
}
