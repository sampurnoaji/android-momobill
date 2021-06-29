package io.android.momobill.data.source.vehicle

import io.android.momobill.data.dto.vehicle.VehicleDetailResponse
import io.android.momobill.data.dto.vehicle.VehiclesResponse
import io.android.momobill.data.service.VehicleService
import io.android.momobill.data.util.ApiClient
import io.android.momobill.vo.ApiResponse
import io.android.momobill.vo.Either

class VehicleRemoteDataSource(private val service: VehicleService) : ApiClient() {

    suspend fun getVehicles(): Either<Exception, VehiclesResponse> {
        return when (val response = call { service.getVehicles() }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun getVehicleDetail(vehicleId: Int): Either<Exception, VehicleDetailResponse> {
        return when (val response = call { service.getVehicleDetail(vehicleId) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}
