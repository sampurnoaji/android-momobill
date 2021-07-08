package io.android.momobill.data.source.vehicle

import io.android.momobill.data.mapper.vehicle.VehicleDetailResponseMapper
import io.android.momobill.data.mapper.vehicle.VehicleResponseMapper
import io.android.momobill.data.params.vehicle.AddVehicleParams
import io.android.momobill.data.request.vehicle.AddVehicleRequest
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.domain.repository.VehicleRepository
import io.android.momobill.vo.Either

class VehicleRepositoryImpl(
    private val remoteDataSource: VehicleRemoteDataSource,
    private val vehicleResponseMapper: VehicleResponseMapper,
    private val vehicleDetailResponseMapper: VehicleDetailResponseMapper
) : VehicleRepository {

    override suspend fun getVehicles(): Either<Exception, List<Vehicle>> {
        return when (val result = remoteDataSource.getVehicles()) {
            is Either.Success -> Either.Success(vehicleResponseMapper(result.data))
            is Either.Failure -> Either.Failure(result.cause)
        }
    }

    override suspend fun getVehicleDetail(vehicleId: Int): Either<Exception, VehicleDetail> {
        return when (val result = remoteDataSource.getVehicleDetail(vehicleId)) {
            is Either.Success -> Either.Success(vehicleDetailResponseMapper(result.data))
            is Either.Failure -> Either.Failure(result.cause)
        }
    }

    override suspend fun addVehicle(params: AddVehicleParams): Either<Exception, Boolean> {
        val request = AddVehicleRequest(
            name = params.name
        )
        return when (val result = remoteDataSource.addVehicle(request)) {
            is Either.Success -> Either.Success(result.data.status == 200)
            is Either.Failure -> Either.Failure(result.cause)
        }
    }
}
