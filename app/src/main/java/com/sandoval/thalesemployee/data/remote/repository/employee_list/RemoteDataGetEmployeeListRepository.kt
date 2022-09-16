package com.sandoval.thalesemployee.data.remote.repository.employee_list

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.repository.employee_list.IGetEmployeeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataGetEmployeeListRepository @Inject constructor(
    private val thalesEmployeeService: ThalesEmployeeService
) : IGetEmployeeListRepository {
    override suspend fun getEmployeeList(): Flow<Either<Failure, List<DData>>> = flow {
        val res = thalesEmployeeService.getEmployeesList()
        emit(
            when (res.isSuccessful) {
                true -> {
                    res.body()?.let {
                        Either.Right(it.data.map { a -> a.toDomainObject() })
                    } ?: Either.Left(Failure.DataError)
                }
                false -> {
                    Either.Left(Failure.ServerError)
                }
            }
        )
    }
}
