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
                employeeAge = 61,
                employeeName = "Tiger Nixon",
                employeeSalary = 320800,
                id = 1,
                profileImage = ""
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

}