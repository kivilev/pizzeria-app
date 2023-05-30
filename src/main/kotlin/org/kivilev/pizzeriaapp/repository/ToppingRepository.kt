/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.repository

import org.kivilev.pizzeriaapp.model.Topping
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ToppingRepository : JpaRepository<Topping, UUID> {
    fun findToppingsByIdIn(toppingIds: Set<UUID>): List<Topping>
}
