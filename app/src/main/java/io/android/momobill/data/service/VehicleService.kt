package io.android.momobill.data.service

import io.android.momobill.data.dto.vehicle.VehiclesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface VehicleService {
    @Headers("mock:true")
    @GET("vehicles")
    suspend fun getVehicles(): Response<VehiclesResponse>
}
