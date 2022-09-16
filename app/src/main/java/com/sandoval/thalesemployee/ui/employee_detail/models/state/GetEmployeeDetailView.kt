package com.sandoval.thalesemployee.ui.employee_detail.models.state

import com.sandoval.thalesemployee.ui.employee_detail.models.DDataDetailPresentation

data class GetEmployeeDetailView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val dataDetail: DDataDetailPresentation? = null
)
