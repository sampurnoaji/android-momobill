package io.android.momobill.data.dto.vehicle

import com.squareup.moshi.Json

data class VehicleDetailResponse(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "brand")
    val brand: String? = null,
    @field:Json(name = "year")
    val year: String? = null,
    @field:Json(name = "color")
    val color: String? = null,
)
