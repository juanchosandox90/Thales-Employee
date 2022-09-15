package com.sandoval.thalesemployee.data.models.employee_list

data class EmployeeListResponse(
    val data: List<Data>,
    val message: String,
    val status: String
)