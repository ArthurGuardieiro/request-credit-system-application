package me.dio1.request.credit.system.service

import me.dio1.request.credit.system.entity.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)

}