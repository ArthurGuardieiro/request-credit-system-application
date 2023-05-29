package me.dio1.request.credit.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio1.request.credit.system.entity.Address
import me.dio1.request.credit.system.entity.Customer
import me.dio1.request.credit.system.exception.BusinessException
import me.dio1.request.credit.system.repository.CustomerRepository
import me.dio1.request.credit.system.service.impl.customerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: customerService

    @Test
    fun `should create customer`(){
        //given
        val fakerCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakerCustomer

        //when
        val actual: Customer = customerService.save(fakerCustomer)


        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakerCustomer)
        verify(exactly = 1) { customerRepository.save(fakerCustomer) }
    }

    @Test
    fun `should find customer by id`(){
        //given
        val fakerId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakerId)
        every { customerRepository.findById(fakerId) } returns Optional.of(fakeCustomer)
        //when
        val actual: Customer = customerService.findById(fakerId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakerId) }
    }

    @Test
    fun `should not find customer by invalida id and throw BusinessException`(){
        //given
        val fakerId: Long = Random().nextLong()
        every { customerRepository.findById(fakerId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakerId) }
            .withMessage("Id $fakerId not found")
        verify(exactly = 1) { customerRepository.findById(fakerId) }
    }

    @Test
    fun `should delet customer by id`(){
        //given
        val fakerId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakerId)
        every { customerRepository.findById(fakerId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakerId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakerId) }
        verify (exactly = 1){ customerRepository.delete(fakeCustomer) }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "Cami",
            lastName: String = "Cavalcante",
            cpf: String = "28475934625",
            email: String = "camila@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da Cami",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }

}