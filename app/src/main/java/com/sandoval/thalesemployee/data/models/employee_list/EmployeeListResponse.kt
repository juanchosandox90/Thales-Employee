package com.sandoval.thalesemployee.data.models.employee_list

import com.sandoval.thalesemployee.domain.models.employee_list.DEmployeeListResponse

data class EmployeeListResponse(
    val data: List<Data>,
    val message: String,
    val status: String
) {
    fun toDomainObject() = DEmployeeListResponse(
        data = data.map { it.toDomainObject() },
        message = message,
        status = status
    )
}