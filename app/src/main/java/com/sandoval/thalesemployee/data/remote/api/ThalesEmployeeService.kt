package com.sandoval.thalesemployee.data.remote.api

import com.sandoval.thalesemployee.commons.EMPLOYEE_LIST_PATH
import com.sandoval.thalesemployee.data.models.employee_list.EmployeeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ThalesEmployeeService {

    //Get Employee List
    @GET(EMPLOYEE_LIST_PATH)
    suspend fun getEmployeesList(): Response<EmployeeListResponse>

}