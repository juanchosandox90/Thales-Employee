package com.sandoval.thalesemployee.domain.usecase.employee_detail

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.base.BaseUseCase
import com.sandoval.thalesemployee.domain.models.employee_detail.DData
import com.sandoval.thalesemployee.domain.repository.employee_detail.IGetEmployeeDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmployeeDetailUseCase @Inject constructor(
    private val iGetEmployeeDetailRepository: IGetEmployeeDetailRepository
) : BaseUseCase<Int, DData>() {
    override suspend fun run(params: Int): Flow<Either<Failure, DData>> {
        return iGetEmployeeDetailRepository.getEmployeeDetail(params)
    }
}
