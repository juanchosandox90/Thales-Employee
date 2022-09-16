package com.sandoval.thalesemployee.ui.mapper

import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.ui.employee_detail.models.DDataDetailPresentation
import com.sandoval.thalesemployee.ui.employee_list.models.DDataPresentation

fun DData.toPresentation() = DDataPresentation(
    employeeAge = employeeAge ?: 0,
    employeeName = employeeName ?: "",
    employeeSalary = employeeSalary ?: 0,
    id = id ?: 0,
    profileImage = profileImage ?: ""
)

fun com.sandoval.thalesemployee.domain.models.employee_detail.DData.toPresentation() = DDataDetailPresentation(
    employeeAge = employeeAge ?: 0,
    employeeName = employeeName ?: "",
    employeeSalary = employeeSalary ?: 0,
    id = id ?: 0,
    profileImage = profileImage ?: ""
)