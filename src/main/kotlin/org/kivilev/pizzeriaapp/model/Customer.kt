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

@Table(name = "customer")
@Entity
class Customer(
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    var id: UUID? = null,

    @Column(name = "full_name", nullable = false)
    val fullName: String,

    @Column(name = "phone_number")
    var phoneNumber: String? = null,

    @Column(name = "email")
    var email: String? = null
) {
    constructor() : this(null, "", null, null)
}
