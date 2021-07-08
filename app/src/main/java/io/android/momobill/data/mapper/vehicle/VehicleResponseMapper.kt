package io.android.momobill.data.mapper.vehicle

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.dto.vehicle.VehiclesResponse
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.util.extension.orZero

class VehicleResponseMapper : Mapper<VehiclesResponse, List<Vehicle>> {

    override fun invoke(dto: VehiclesResponse): List<Vehicle> {
        return dto.vehicles?.map {
            Vehicle(
                id = it.id.orZero(),
                name = it.name.orEmpty()
            )
        } ?: emptyList()
    }
}
