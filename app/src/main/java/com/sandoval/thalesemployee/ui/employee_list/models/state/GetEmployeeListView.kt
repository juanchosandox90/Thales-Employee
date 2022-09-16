package com.sandoval.thalesemployee.ui.employee_list.models.state

import com.sandoval.thalesemployee.ui.employee_list.models.DDataPresentation

data class GetEmployeeListView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val data: List<DDataPresentation>? = null
)
