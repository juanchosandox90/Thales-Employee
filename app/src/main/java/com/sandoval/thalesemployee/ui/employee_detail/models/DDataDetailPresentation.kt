package com.sandoval.thalesemployee.ui.employee_detail.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DDataDetailPresentation(
    val employeeAge: Int?,
    val employeeName: String?,
    val employeeSalary: Int?,
    val id: Int?,
    val profileImage: String?
) : Parcelable