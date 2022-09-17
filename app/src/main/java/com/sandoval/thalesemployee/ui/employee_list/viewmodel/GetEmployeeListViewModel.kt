package com.sandoval.thalesemployee.ui.employee_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.usecase.employee_list.GetEmployeeListUseCase
import com.sandoval.thalesemployee.ui.employee_list.models.state.GetEmployeeListView
import com.sandoval.thalesemployee.ui.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class GetEmployeeListViewModel @Inject constructor(
    private val getEmployeeListUseCase: GetEmployeeListUseCase
) : ViewModel() {

    private val job = Job()

    private val _employeeListModel = MutableLiveData<GetEmployeeListView>()
    val employeeListModel: LiveData<GetEmployeeListView> get() = _employeeListModel

    private val _listEmployees = MutableLiveData<List<DData>>()
    val listEmployees: LiveData<List<DData>> get() = _listEmployees

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        getData()
    }

    fun getData() {
        _employeeListModel.value = GetEmployeeListView(loading = true)
        getEmployeeListUseCase(job, Unit) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(data: List<DData>) {
        if (data.isNotEmpty()) {
            val presentation = data.map { it.toPresentation() }
            _employeeListModel.value =
                GetEmployeeListView(data = presentation)
            _listEmployees.value = data
        } else {
            _employeeListModel.value = GetEmployeeListView(isEmpty = true)
        }
    }

    private fun handleFailure(failure: Failure) {
        _employeeListModel.value = GetEmployeeListView(errorMessage = failure.toString())
        _errorMessage.value = failure.toString()

    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}