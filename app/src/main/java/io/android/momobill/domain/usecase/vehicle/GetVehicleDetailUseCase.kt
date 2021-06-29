package io.android.momobill.domain.usecase.vehicle

import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.domain.repository.VehicleRepository
import io.android.momobill.vo.Either

class GetVehicleDetailUseCase(private val repository: VehicleRepository) : UseCase<Int, VehicleDetail>() {

    override suspend fun invoke(params: Int): Either<Exception, VehicleDetail> {
        return repository.getVehicleDetail(params)
    }
}
