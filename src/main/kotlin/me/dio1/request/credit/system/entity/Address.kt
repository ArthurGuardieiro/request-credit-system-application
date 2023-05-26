package me.dio1.request.credit.system.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity


@Embeddable
data class Address(

    @Column(nullable = false) var zipCode: String = "",
    @Column(nullable = false) var street: String = ""

)
