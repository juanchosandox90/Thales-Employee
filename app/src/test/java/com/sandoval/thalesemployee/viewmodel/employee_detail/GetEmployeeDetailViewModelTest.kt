package com.sandoval.thalesemployee.viewmodel.employee_detail

import com.google.common.truth.Truth
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import com.sandoval.thalesemployee.domain.usecase.employee_detail.GetEmployeeDetailUseCase
import com.sandoval.thalesemployee.ui.employee_detail.viewmodel.GetEmployeeDetailViewModel
import com.sandoval.thalesemployee.ui.mapper.toPresentation
import com.sandoval.thalesemployee.utils.UnitTest
import com.sandoval.thalesemployee.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class GetEmployeeDetailViewModelTest : UnitTest() {

    @MockK
    private lateinit var getEmployeeDetailUseCase: GetEmployeeDetailUseCase

    private lateinit var getEmployeeDetailViewModel: GetEmployeeDetailViewModel

    private lateinit var dataDetail: DData

    @Before
    fun setUp() {
        dataDetail = DData(
            employee_age = 61,
            employee_name = "Tiger Nixon",
            employee_salary = 320800,
            id = 1,
            profile_image = ""
        )
        getEmployeeDetailViewModel = GetEmployeeDetailViewModel(getEmployeeDetailUseCase)
    }

    @Test
    fun `getDataDetail should return actual detail of the employee`() {
        every { getEmployeeDetailUseCase(any(), 1, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(
                Either.Right(dataDetail)
            )
        }
        getEmployeeDetailViewModel.getDataDetail(1)
        val res = getEmployeeDetailViewModel.getEmployeeDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.dataDetail).isEqualTo(dataDetail.toPresentation())
    }


    @Test
    fun `getDataDetail should show error view when error occurs`() {
        every { getEmployeeDetailUseCase(any(), 1, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Left(Failure.ServerError))
        }
        getEmployeeDetailViewModel.getDataDetail(1)
        val res = getEmployeeDetailViewModel.getEmployeeDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.dataDetail).isNull()
    }

    @Test
    fun `getDataDetail should show error connection view when a error network connection occurs`() {
        every { getEmployeeDetailUseCase(any(), 1, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Left(Failure.NetworkConnection))
        }
        getEmployeeDetailViewModel.getDataDetail(1)
        val res = getEmployeeDetailViewModel.getEmployeeDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.dataDetail).isNull()
    }
}