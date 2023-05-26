/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.controller.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Order state")
enum class OrderStateDto {
    CREATED, DELIVERING, DELIVERED, CANCELLED
}
