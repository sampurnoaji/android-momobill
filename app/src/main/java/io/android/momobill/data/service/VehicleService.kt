package io.android.momobill.data.service

import io.android.momobill.data.dto.BaseResponse
import io.android.momobill.data.dto.vehicle.VehicleDetailResponse
import io.android.momobill.data.dto.vehicle.VehiclesResponse
import io.android.momobill.data.request.vehicle.AddVehicleRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface VehicleService {
    @Headers("mock:true")
    @GET("vehicles")
    suspend fun getVehicles(): Response<VehiclesResponse>

    @Headers("mock:true")
    @GET("vehicles/{id}")
    suspend fun getVehicleDetail(@Path("id") vehicleId: Int): Response<VehicleDetailResponse>

    @Headers("mock:true")
    @POST("vehicles")
    suspend fun addVehicle(@Body request: AddVehicleRequest): Response<BaseResponse>
}
