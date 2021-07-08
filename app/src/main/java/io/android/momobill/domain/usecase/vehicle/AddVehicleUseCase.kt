package io.android.momobill.domain.usecase.vehicle

import io.android.momobill.abstraction.UseCase
import io.android.momobill.data.params.vehicle.AddVehicleParams
import io.android.momobill.domain.repository.VehicleRepository
import io.android.momobill.vo.Either

class AddVehicleUseCase(private val repository: VehicleRepository) :
    UseCase<AddVehicleParams, Boolean> {

    override suspend fun invoke(params: AddVehicleParams): Either<Exception, Boolean> {
        return repository.addVehicle(params)
    }
}
