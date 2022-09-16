package com.sandoval.thalesemployee.domain.repository.employee_detail

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import kotlinx.coroutines.flow.Flow

interface IGetEmployeeDetailRepository {

    //Remote get employee detail Service
    suspend fun getEmployeeDetail(id: Int): Flow<Either<Failure, DData>>
}
