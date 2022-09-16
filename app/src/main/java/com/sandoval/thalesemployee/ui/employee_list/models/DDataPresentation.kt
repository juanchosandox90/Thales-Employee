package com.sandoval.thalesemployee.ui.employee_list.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DDataPresentation(
    val employeeAge: Int?,
    val employeeName: String?,
    val employeeSalary: Int?,
    val id: Int?,
    val profileImage: String?
) : Parcelable