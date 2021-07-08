package io.android.momobill.domain.repository

import io.android.momobill.data.params.vehicle.AddVehicleParams
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.vo.Either

interface VehicleRepository {
    suspend fun getVehicles(): Either<Exception, List<Vehicle>>
    suspend fun getVehicleDetail(vehicleId: Int): Either<Exception, VehicleDetail>
    suspend fun addVehicle(params: AddVehicleParams): Either<Exception, Boolean>
}
