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
class CustomerMapper {
    fun fromDto(customerCreateRequestDto: CustomerCreateRequestDto): Customer {
        return Customer(
            null,
            customerCreateRequestDto.fullName,
            customerCreateRequestDto.email,
            customerCreateRequestDto.phoneNumber
        )
    }

    fun toDto(customer: Customer): CustomerResponseDto {
        return CustomerResponseDto(
            customer.id!!,
            customer.fullName,
            customer.phoneNumber,
            customer.email
        )
    }

    fun toDto(customers: List<Customer>): List<CustomerResponseDto> {
        return customers.map { customer -> toDto(customer) }
    }
}
