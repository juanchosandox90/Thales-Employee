package com.sandoval.thalesemployee.data.remote.repository

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.repository.IGetEmployeeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataGetEmployeeListRepository @Inject constructor(
    private val thalesEmployeeService: ThalesEmployeeService
) : IGetEmployeeListRepository {
    override suspend fun getEmployeeList(): Flow<Either<Failure, List<DData>>> = flow {
        val res = thalesEmployeeService.getEmployeesList()
        //TODO Emit the result mapping the data through the domain
    }
}