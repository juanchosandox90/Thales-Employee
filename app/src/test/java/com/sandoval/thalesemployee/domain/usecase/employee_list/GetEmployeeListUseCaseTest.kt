package com.sandoval.thalesemployee.domain.usecase.employee_list

import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.repository.employee_list.IGetEmployeeListRepository
import com.sandoval.thalesemployee.utils.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEmployeeListUseCaseTest : UnitTest() {
    private lateinit var getEmployeeListUseCase: GetEmployeeListUseCase

    @MockK
    private lateinit var iGetEmployeeListRepository: IGetEmployeeListRepository

    @Before
    fun setUp() {
        getEmployeeListUseCase = GetEmployeeListUseCase(iGetEmployeeListRepository)
    }

    @Test
    fun `should call getEmployeeList from repository`() =
        runTest {
            coEvery {
                iGetEmployeeListRepository.getEmployeeList()
            } returns flow {
                emit(
                    Either.Right(
                        listOf(
                            DData(
                                employeeAge = 61,
                                employeeName = "Tiger Nixon",
                                employeeSalary = 320800,
                                id = 1,
                                profileImage = ""
                            )
                        )
                    )
                )
            }
            getEmployeeListUseCase.run(Unit)
            coVerify(exactly = 1) { iGetEmployeeListRepository.getEmployeeList() }
        }
}