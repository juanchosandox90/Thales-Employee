package com.sandoval.thalesemployee.ui.mapper

import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.ui.employee_detail.models.DDataDetailPresentation
import com.sandoval.thalesemployee.ui.employee_list.models.DDataPresentation

fun DData.toPresentation() = DDataPresentation(
    employee_age = employee_age ?: 0,
    employee_name = employee_name ?: "",
    employee_salary = employee_salary ?: 0,
    id = id ?: 0,
    profile_image = profile_image ?: ""
)

fun com.sandoval.thalesemployee.domain.models.employee_detail.DData.toPresentation() = DDataDetailPresentation(
    employee_age = employee_age ?: 0,
    employee_name = employee_name ?: "",
    employee_salary = employee_salary ?: 0,
    id = id ?: 0,
    profile_image = profile_image ?: ""
)