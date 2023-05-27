/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.model

import java.util.UUID

interface ToppingsUniqueCustomersReport {
    val id: UUID
    val code: String
    val fullName: String
    val uniqueCustomersCount: Int
}
