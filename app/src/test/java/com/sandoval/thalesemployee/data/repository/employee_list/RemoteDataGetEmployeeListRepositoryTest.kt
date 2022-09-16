package com.sandoval.thalesemployee.data.repository.employee_list

import com.google.common.truth.Truth
import com.sandoval.thalesemployee.data.models.employee_list.Data
import com.sandoval.thalesemployee.data.models.employee_list.EmployeeListResponse
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.remote.repository.employee_list.RemoteDataGetEmployeeListRepository
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.utils.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataGetEmployeeListRepositoryTest : UnitTest() {

    private lateinit var remoteDataGetEmployeeListRepository: RemoteDataGetEmployeeListRepository
    private lateinit var employeeListResponse: EmployeeListResponse

    @MockK
    private lateinit var thalesEmployeeService: ThalesEmployeeService

    @MockK
    private lateinit var resultResponse: Response<EmployeeListResponse>

    @Before
    fun setUp() {
        remoteDataGetEmployeeListRepository =
            RemoteDataGetEmployeeListRepository(thalesEmployeeService)
    }

    @Test
    fun `Employees List service with null response body should return data error`() =
        runTest {
            every { resultResponse.body() } returns null
            every { resultResponse.isSuccessful } returns true
            coEvery { thalesEmployeeService.getEmployeesList() } returns resultResponse
            val data = remoteDataGetEmployeeListRepository.getEmployeeList()
            data.collect { a ->
                Truth.assertThat(a).isEqualTo(Either.Left(Failure.DataError))
            }
        }

    @Test
    fun `Employees List Service should return server error when response is not successful`() =
        runTest {
            every { resultResponse.isSuccessful } returns false
            coEvery { thalesEmployeeService.getEmployeesList() } returns resultResponse
            val data = remoteDataGetEmployeeListRepository.getEmployeeList()
            data.collect { a ->
                Truth.assertThat(a).isEqualTo(Either.Left(Failure.ServerError))
            }
        }

    @Test
    fun `Employees List Service should return the employees list successful`() =
        runTest {
            employeeListResponse = EmployeeListResponse(
                data = listOf(
                    Data(
                        employeeAge = 61,
                        employeeName = "Tiger Nixon",
                        employeeSalary = 320800,
                        id = 1,
                        profileImage = ""
                    )
                ),
                message = "Successfully! All records has been fetched.",
                status = "success"
            )
            every { resultResponse.body() } returns employeeListResponse
            every { resultResponse.isSuccessful } returns true
            coEvery { thalesEmployeeService.getEmployeesList() } returns resultResponse
            val data = remoteDataGetEmployeeListRepository.getEmployeeList()
            data.collect { a ->
                Truth.assertThat(a)
                    .isEqualTo(Either.Right(employeeListResponse.data.map { it.toDomainObject() }))
            }
        }
}