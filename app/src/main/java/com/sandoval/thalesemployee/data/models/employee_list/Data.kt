package com.sandoval.thalesemployee.data.models.employee_list

import com.sandoval.thalesemployee.domain.models.employee_list.DData

data class Data(
    val employeeAge: Int?,
    val employeeName: String?,
    val employeeSalary: Int?,
    val id: Int?,
    val profileImage: String?
) {
    fun toDomainObject() = DData(
        employeeAge = employeeAge ?: 0,
        employeeName = employeeName ?: "",
        employeeSalary = employeeSalary ?: 0,
        id = id ?: 0,
        profileImage = profileImage ?: ""
    )
}
