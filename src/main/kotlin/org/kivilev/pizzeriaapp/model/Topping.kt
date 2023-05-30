/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */
package org.kivilev.pizzeriaapp.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Table(name = "topping")
@Entity
class Topping(
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    var id: UUID? = null,

    @Column(name = "code", nullable = false, unique = false, length = 100)
    val code: String,

    @Column(name = "full_name", nullable = false, length = 1000)
    val fullName: String
)
