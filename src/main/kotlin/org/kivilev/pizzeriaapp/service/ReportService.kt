/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.service

import org.kivilev.pizzeriaapp.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class ReportService(
    private val orderRepository: OrderRepository

) {
    fun getToppingsUniqueCustomersReport() =
        orderRepository.getToppingsUniqueCustomersReport()
}
