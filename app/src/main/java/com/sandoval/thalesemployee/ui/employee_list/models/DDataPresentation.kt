package com.sandoval.thalesemployee.ui.employee_list.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DDataPresentation(
    val employee_age: Int?,
    val employee_name: String?,
    val employee_salary: Int?,
    val id: Int?,
    val profile_image: String?
) : Parcelable