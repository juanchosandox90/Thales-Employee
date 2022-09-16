package com.sandoval.thalesemployee.data.remote.repository.employee_detail

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import com.sandoval.thalesemployee.domain.repository.employee_detail.IGetEmployeeDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataGetEmployeeDetailRepository @Inject constructor(
    private val thalesEmployeeService: ThalesEmployeeService
) : IGetEmployeeDetailRepository {
    override suspend fun getEmployeeDetail(id: Int): Flow<Either<Failure, DData>> = flow {
        val res = thalesEmployeeService.getEmployeeDetail(id)
        emit(
            when (res.isSuccessful) {
                true -> {
                    res.body()?.let {
                        when {
                            it.data != null -> {
                                Either.Right(it.data.toDomainObject())
                            }
                            else -> {
                                Either.Left(Failure.DataError)
                            }
                        }
                    } ?: Either.Left(Failure.DataError)
                }
                false -> {
                    Either.Left(Failure.ServerError)
                }
            }
        )
    }

}