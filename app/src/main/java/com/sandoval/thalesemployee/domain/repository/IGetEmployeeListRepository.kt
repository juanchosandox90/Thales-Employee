package com.sandoval.thalesemployee.domain.repository

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import kotlinx.coroutines.flow.Flow

interface IGetEmployeeListRepository {

    //Remote get employee list Service
    suspend fun getEmployeeList(): Flow<Either<Failure, List<DData>>>

}
