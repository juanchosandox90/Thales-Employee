package com.sandoval.thalesemployee.data.remote.api

import com.sandoval.thalesemployee.commons.EMPLOYEE_DETAIL_PATH
import com.sandoval.thalesemployee.commons.EMPLOYEE_LIST_PATH
import com.sandoval.thalesemployee.data.models.employee_detail.EmployeeDetailResponse
import com.sandoval.thalesemployee.data.models.employee_list.EmployeeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ThalesEmployeeService {

    //Get Employee List
    @GET(EMPLOYEE_LIST_PATH)
    suspend fun getEmployeesList(): Response<EmployeeListResponse>

    //Get Employee Detail
    @GET(EMPLOYEE_DETAIL_PATH)
    suspend fun getEmployeeDetail(@Path("id") id: Int): Response<EmployeeDetailResponse>

}
