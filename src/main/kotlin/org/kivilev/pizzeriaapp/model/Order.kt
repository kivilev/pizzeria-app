/*
 * Copyright (c) 2023-05
 * Author: Kivilev Denis <kivilev.d@gmail.com>
 */

package org.kivilev.pizzeriaapp.model

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import org.hibernate.annotations.UuidGenerator
import java.time.ZonedDateTime
import java.util.UUID

@Table(name = "orders")
@Entity
class Order(
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType::class)
    @Column(name = "state", nullable = true)
    var state: OrderState = OrderState.CREATED,

    @Column(name = "email", nullable = false, length = 100)
    var email: String,

    @Column(name = "created_dtime", nullable = false)
    var createdDateTime: ZonedDateTime,

    @ManyToOne
    var customer: Customer,

    @ManyToMany
    @JoinTable(
        name = "orders_topping",
        joinColumns = [JoinColumn(name = "orders_id")],
        inverseJoinColumns = [JoinColumn(name = "topping_id")]
    )
    var toppings: List<Topping>
)
