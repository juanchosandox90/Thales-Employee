package com.sandoval.thalesemployee.data.repository.employee_detail

import com.google.common.truth.Truth
import com.sandoval.thalesemployee.data.models.employee_detail.Data
import com.sandoval.thalesemployee.data.models.employee_detail.EmployeeDetailResponse
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.remote.repository.employee_detail.RemoteDataGetEmployeeDetailRepository
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.utils.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteDataGetEmployeeDetailRepositoryTest : UnitTest() {

    private lateinit var remoteDataGetEmployeeDetailRepository: RemoteDataGetEmployeeDetailRepository
    private lateinit var getEmployeeDetailResponse: EmployeeDetailResponse

    @MockK
    private lateinit var thalesEmployeeService: ThalesEmployeeService

    @MockK
    private lateinit var resultResponse: Response<EmployeeDetailResponse>

    @Before
    fun setUp() {
        remoteDataGetEmployeeDetailRepository =
            RemoteDataGetEmployeeDetailRepository(thalesEmployeeService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get employee detail service should return server error when response is not successful`() =
        runBlockingTest {
            every { resultResponse.isSuccessful } returns false
            coEvery { thalesEmployeeService.getEmployeeDetail(1) } returns resultResponse

            val results =
                remoteDataGetEmployeeDetailRepository.getEmployeeDetail(1)

            results.collect { a ->
                Truth.assertThat(a).isEqualTo(Either.Left(Failure.ServerError))
            }
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `get employee detail service should get employee detail regarding specific id`() =
        runBlockingTest {
            getEmployeeDetailResponse = EmployeeDetailResponse(
                data = Data(
                    employeeAge = 32,
                    employeeName = "Tiger Nixon",
                    employeeSalary = 32000,
                    id = 1,
                    profileImage = ""
                ),
                message = "Successfully! Record has been fetched.",
                status = "success"
            )

            every { resultResponse.body() } returns getEmployeeDetailResponse
            every { resultResponse.isSuccessful } returns true
            coEvery { thalesEmployeeService.getEmployeeDetail(1) } returns resultResponse

            val results =
                remoteDataGetEmployeeDetailRepository.getEmployeeDetail(1)
            results.collect { a ->
                Truth.assertThat(a)
                    .isEqualTo(getEmployeeDetailResponse.data?.let { Either.Right(it.toDomainObject()) })
            }
        }
}