package com.sandoval.thalesemployee.data.models.employee_detail

import com.sandoval.thalesemployee.domain.models.employee_detail.DEmployeeDetailResponse

data class EmployeeDetailResponse(
    val data: Data?,
    val message: String,
    val status: String
) {
    fun toDomainObject() = DEmployeeDetailResponse(
        data = data?.toDomainObject(),
        message = message,
        status = status
    )
}
