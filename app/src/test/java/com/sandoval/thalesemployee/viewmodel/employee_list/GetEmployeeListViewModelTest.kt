package com.sandoval.thalesemployee.viewmodel.employee_list

import com.google.common.truth.Truth
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.usecase.employee_list.GetEmployeeListUseCase
import com.sandoval.thalesemployee.ui.employee_list.viewmodel.GetEmployeeListViewModel
import com.sandoval.thalesemployee.ui.mapper.toPresentation
import com.sandoval.thalesemployee.utils.UnitTest
import com.sandoval.thalesemployee.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class GetEmployeeListViewModelTest : UnitTest() {

    @MockK
    private lateinit var getEmployeeListUseCase: GetEmployeeListUseCase

    private lateinit var getEmployeeListViewModel: GetEmployeeListViewModel

    private lateinit var data: List<DData>

    @Before
    fun setUp() {
        data = listOf(
            DData(
                employee_age = 61,
                employee_name = "Tiger Nixon",
                employee_salary = 320800,
                id = 1,
                profile_image = ""
            )
        )
        getEmployeeListViewModel = GetEmployeeListViewModel(getEmployeeListUseCase)
    }

    @Test
    fun `getData should return actual employee list`() {
        every { getEmployeeListUseCase(any(), Unit, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Right(data))
        }
        getEmployeeListViewModel.getData()
        val res = getEmployeeListViewModel.employeeListModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.data).isEqualTo(data.map { it.toPresentation() })
    }

    @Test
    fun `getData should show empty view when no results are found`() {
        every { getEmployeeListUseCase(any(), Unit, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Right(emptyList()))
        }
        getEmployeeListViewModel.getData()
        val res = getEmployeeListViewModel.employeeListModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isTrue()
        Truth.assertThat(res.data).isNull()
    }

    @Test
    fun `getData should show error view when error occurs`() {
        every { getEmployeeListUseCase(any(), Unit, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Left(Failure.ServerError))
        }
        getEmployeeListViewModel.getData()
        val res = getEmployeeListViewModel.employeeListModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.data).isNull()
    }

    @Test
    fun `getData should show error connection view when a error network connection occurs`() {
        every { getEmployeeListUseCase(any(), Unit, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Left(Failure.NetworkConnection))
        }
        getEmployeeListViewModel.getData()
        val res = getEmployeeListViewModel.employeeListModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.data).isNull()
    }

}