/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.service

import jakarta.transaction.Transactional
import org.kivilev.pizzeriaapp.exception.ObjectNotFoundException
import org.kivilev.pizzeriaapp.model.Topping
import org.kivilev.pizzeriaapp.repository.ToppingRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ToppingService(private val toppingRepository: ToppingRepository) {
    @Transactional
    fun addTopping(topping: Topping): Topping {
        return toppingRepository.save(topping)
    }

    @Transactional
    fun removeTopping(toppingId: UUID) {
        val topping = toppingRepository.findById(toppingId)
            .orElseThrow { ObjectNotFoundException("Topping with $toppingId not found") }
        toppingRepository.delete(topping)
    }

    fun getToppings(): List<Topping> {
        return toppingRepository.findAll()
    }

    fun getTopping(toppingId: UUID): Topping {
        return toppingRepository.findById(toppingId)
            .orElseThrow { ObjectNotFoundException("Topping with $toppingId not found") }
    }

    fun findToppingsByIds(toppingIds: Set<UUID>): List<Topping> =
        toppingRepository.findToppingsByIdIn(toppingIds)
}
