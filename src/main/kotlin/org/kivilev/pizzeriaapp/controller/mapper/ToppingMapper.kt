/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.mapper

import org.kivilev.pizzeriaapp.controller.model.ToppingCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.ToppingResponseDto
import org.kivilev.pizzeriaapp.model.Topping
import org.springframework.stereotype.Component

@Component
class ToppingMapper {
    fun fromDto(toppingCreateRequestDto: ToppingCreateRequestDto): Topping =
        Topping(null, toppingCreateRequestDto.code, toppingCreateRequestDto.fullName)

    fun toDto(topping: Topping): ToppingResponseDto =
        ToppingResponseDto(topping.id!!, topping.code, topping.fullName)

    fun toDto(toppings: List<Topping>): List<ToppingResponseDto> =
        toppings.map { topping -> toDto(topping) }
}
