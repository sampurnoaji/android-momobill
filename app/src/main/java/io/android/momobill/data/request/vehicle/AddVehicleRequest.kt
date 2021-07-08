package io.android.momobill.data.request.vehicle

import com.squareup.moshi.Json

data class AddVehicleRequest(
    @field:Json(name = "name")
    val name: String
)
