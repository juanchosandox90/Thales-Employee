package com.sandoval.thalesemployee.data.models.employee_detail

import com.sandoval.thalesemployee.domain.models.employee_detail.DData

data class Data(
    val employee_age: Int?,
    val employee_name: String?,
    val employee_salary: Int?,
    val id: Int?,
    val profile_image: String?
) {
    fun toDomainObject() = DData(
        employee_age = employee_age ?: 0,
        employee_name = employee_name ?: "",
        employee_salary = employee_salary ?: 0,
        id = id ?: 0,
        profile_image = profile_image ?: ""
    )
}
