/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.repository

import jakarta.persistence.LockModeType
import org.kivilev.pizzeriaapp.model.Order
import org.kivilev.pizzeriaapp.model.OrderState
import org.kivilev.pizzeriaapp.model.ToppingsUniqueCustomersReportDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findOrderByCustomer_IdAndState(customerId: UUID, state: OrderState): Optional<Order>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findOrderById(id: UUID): Optional<Order>

    fun findAllByCustomer_IdAndStateIn(customerId: UUID, states: List<OrderState>): List<Order>

    @Query(
        """
        select t.id as id, t.code as code, t.fullName as fullName,  count(distinct o.customer) as uniqueCustomersCount
          from Order o
          join o.toppings t
         where o.state = 'CREATED'
         group by t.id, t.code
         order by t.fullName
    """
    )
    fun getToppingsUniqueCustomersReport(): List<ToppingsUniqueCustomersReportDto>
}
