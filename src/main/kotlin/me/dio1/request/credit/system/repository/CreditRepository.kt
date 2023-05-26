package me.dio1.request.credit.system.repository

import me.dio1.request.credit.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository: JpaRepository<Credit, Long> {

    fun findByCreditCode(creditCode: UUID): Credit?
    @Query(value = "SELECT * FROM credit WHERE customer_id = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>

}