/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.kivilev.pizzeriaapp.controller.mapper.ToppingDtoMapper
import org.kivilev.pizzeriaapp.controller.model.ToppingCreateRequestDto
import org.kivilev.pizzeriaapp.controller.model.ToppingResponseDto
import org.kivilev.pizzeriaapp.service.ToppingService
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
@Tag(name = "topping", description = "the Topping API")
class ToppingController(
    private val toppingService: ToppingService,
    private val toppingDtoMapper: ToppingDtoMapper
) {
    @PostMapping("/api/v1/toppings")
    @Operation(summary = "Create a new one topping")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveTopping(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Topping properties")
        @Valid
        @RequestBody toppingCreateRequestDto: ToppingCreateRequestDto
    ): ToppingResponseDto {
        val newTopping = toppingDtoMapper.fromDto(toppingCreateRequestDto)
        return toppingDtoMapper.toDto(toppingService.addTopping(newTopping))
    }

    @GetMapping("/api/v1/toppings")
    @Operation(summary = "Get list of toppings")
    fun getToppings(): List<ToppingResponseDto> {
        // TODO: add pagination
        return toppingDtoMapper.toDto(toppingService.getToppings())
    }

    @GetMapping("/api/v1/toppings/{id}")
    @Operation(summary = "Get a topping info")
    fun getTopping(
        @Parameter(description = "Topping UUID")
        @PathVariable("id") toppingId: UUID
    ): ToppingResponseDto {
        return toppingDtoMapper.toDto(toppingService.getTopping(toppingId))
    }

    @DeleteMapping("/api/v1/toppings/{id}")
    @Operation(summary = "Delete a topping")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopping(
        @Parameter(description = "Topping UUID")
        @PathVariable("id") toppingId: UUID
    ) {
        toppingService.removeTopping(toppingId)
    }
}
