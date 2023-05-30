/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.service

import jakarta.transaction.Transactional
import org.kivilev.pizzeriaapp.exception.ObjectNotFoundException
import org.kivilev.pizzeriaapp.model.Customer
import org.kivilev.pizzeriaapp.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    @Transactional
    fun addCustomer(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    @Transactional
    fun removeCustomer(customerId: UUID) {
        val customer = customerRepository.findById(customerId)
            .orElseThrow { ObjectNotFoundException("Customer with $customerId not found") }
        customerRepository.delete(customer)
    }

    fun getCustomers(): List<Customer> {
        return customerRepository.findAll()
    }

    fun getCustomer(customerId: UUID): Customer {
        return customerRepository.findById(customerId)
            .orElseThrow { ObjectNotFoundException("Customer with $customerId not found") }
    }
}
