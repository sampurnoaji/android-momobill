package io.android.momobill.data.dto.vehicle

import com.squareup.moshi.Json

data class VehiclesResponse(
    @field:Json(name = "vehicles")
    val vehicles: List<Vehicle>? = null
) {
    data class Vehicle(
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "name")
        val name: String? = null
    )
}
