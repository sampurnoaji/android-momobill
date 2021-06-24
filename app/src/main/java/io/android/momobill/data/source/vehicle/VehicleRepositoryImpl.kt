package io.android.momobill.data.source.vehicle

import io.android.momobill.data.mapper.vehicle.VehicleResponseMapper
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.domain.repository.VehicleRepository
import io.android.momobill.vo.Either

class VehicleRepositoryImpl(
    private val remoteDataSource: VehicleRemoteDataSource,
    private val vehicleResponseMapper: VehicleResponseMapper
) : VehicleRepository {

    override suspend fun getVehicles(): Either<Exception, List<Vehicle>> {
        return when (val result = remoteDataSource.getVehicles()) {
            is Either.Success -> Either.Success(vehicleResponseMapper(result.data))
            is Either.Failure -> Either.Failure(result.cause)
        }
    }
}
