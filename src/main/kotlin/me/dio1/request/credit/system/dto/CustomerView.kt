package me.dio1.request.credit.system.dto

import jakarta.validation.constraints.Email
import me.dio1.request.credit.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerView (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val zipCode: String,
    val street: String
) {

    constructor(customer: Customer): this (
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        zipCode = customer.address.zipCode,
        street = customer.address.street
    )

}
