package com.sandoval.thalesemployee.ui.employee_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import com.sandoval.thalesemployee.domain.usecase.employee_detail.GetEmployeeDetailUseCase
import com.sandoval.thalesemployee.ui.employee_detail.models.state.GetEmployeeDetailView
import com.sandoval.thalesemployee.ui.employee_list.models.state.GetEmployeeListView
import com.sandoval.thalesemployee.ui.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class GetEmployeeDetailViewModel @Inject constructor(
    private val getEmployeeDetailUseCase: GetEmployeeDetailUseCase
) : ViewModel() {

    private val job = Job()

    private val _getEmployeeDetailViewModel = MutableLiveData<GetEmployeeDetailView>()
    val getEmployeeDetailViewModel: LiveData<GetEmployeeDetailView> get() = _getEmployeeDetailViewModel

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getDataDetail(id: Int) {
        _getEmployeeDetailViewModel.value = GetEmployeeDetailView(loading = true)
        getEmployeeDetailUseCase(job, id) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(dataDetail: DData?) {
        if (dataDetail != null) {
            _getEmployeeDetailViewModel.value =
                GetEmployeeDetailView(dataDetail = dataDetail.toPresentation())
        } else {
            _getEmployeeDetailViewModel.value = GetEmployeeDetailView(isEmpty = true)
        }
    }

    private fun handleFailure(failure: Failure) {
        _getEmployeeDetailViewModel.value = GetEmployeeDetailView(errorMessage = failure.toString())
        _errorMessage.value = failure.toString()
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}
