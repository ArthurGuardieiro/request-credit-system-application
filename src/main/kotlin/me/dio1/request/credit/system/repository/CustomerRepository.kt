package me.dio1.request.credit.system.repository

import me.dio1.request.credit.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long>