package com.sandoval.thalesemployee.domain.usecase.employee_detail

import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import com.sandoval.thalesemployee.domain.repository.employee_detail.IGetEmployeeDetailRepository
import com.sandoval.thalesemployee.utils.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GetEmployeeDetailUseCaseTest : UnitTest() {
    private lateinit var getEmployeeDetailUseCase: GetEmployeeDetailUseCase

    @MockK
    private lateinit var iGetEmployeeDetailRepository: IGetEmployeeDetailRepository

    @Before
    fun setUp() {
        getEmployeeDetailUseCase = GetEmployeeDetailUseCase(iGetEmployeeDetailRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should call getEmployeeDetail from repository`() = runBlockingTest {
        coEvery { iGetEmployeeDetailRepository.getEmployeeDetail(1) } returns flow {
            emit(
                Either.Right(
                    DData(
                        employeeAge = 32,
                        employeeName = "Tiger Nixon",
                        employeeSalary = 32000,
                        id = 1,
                        profileImage = ""
                    )
                )
            )
        }
        getEmployeeDetailUseCase.run(1)
        coVerify(exactly = 1) { iGetEmployeeDetailRepository.getEmployeeDetail(1) }
    }

}