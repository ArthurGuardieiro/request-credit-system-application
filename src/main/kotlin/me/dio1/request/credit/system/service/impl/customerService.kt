package me.dio1.request.credit.system.service.impl

import me.dio1.request.credit.system.entity.Customer
import me.dio1.request.credit.system.exception.BusinessException
import me.dio1.request.credit.system.repository.CustomerRepository
import me.dio1.request.credit.system.service.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class customerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("Id $id not found")
        }
    }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}