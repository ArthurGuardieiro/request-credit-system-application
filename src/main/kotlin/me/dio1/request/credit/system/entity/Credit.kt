package me.dio1.request.credit.system.entity

import jakarta.persistence.*
import me.dio1.request.credit.system.enumeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
//@Table(name = "Credito")
data class Credit (

    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) var creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) var dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne val customer:  Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null

    )
