package io.android.momobill.domain.usecase.vehicle

import io.android.momobill.abstraction.NoParamsUseCase
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.domain.repository.VehicleRepository
import io.android.momobill.vo.Either

class GetVehiclesUseCase(private val repository: VehicleRepository) :
    NoParamsUseCase<List<Vehicle>>() {

    override suspend fun invoke(): Either<Exception, List<Vehicle>> {
        return repository.getVehicles()
    }
}
