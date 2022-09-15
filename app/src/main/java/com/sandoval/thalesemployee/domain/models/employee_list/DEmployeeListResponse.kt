package com.sandoval.thalesemployee.domain.models.employee_list

data class DEmployeeListResponse(
    val data: List<DData>,
    val message: String,
    val status: String
)
