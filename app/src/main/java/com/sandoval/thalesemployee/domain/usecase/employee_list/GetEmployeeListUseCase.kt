package com.sandoval.thalesemployee.domain.usecase.employee_list

import com.sandoval.thalesemployee.data.network.Failure
import com.sandoval.thalesemployee.data.utils.Either
import com.sandoval.thalesemployee.domain.models.employee_list.DData
import com.sandoval.thalesemployee.domain.repository.employee_list.IGetEmployeeListRepository
import com.sandoval.thalesemployee.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmployeeListUseCase @Inject constructor(
    private val iGetEmployeeListRepository: IGetEmployeeListRepository
) : BaseUseCase<Unit, List<DData>>() {
    override suspend fun run(params: Unit): Flow<Either<Failure, List<DData>>> {
        return iGetEmployeeListRepository.getEmployeeList()
    }
}
