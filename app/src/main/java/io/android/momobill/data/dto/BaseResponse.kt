package io.android.momobill.data.dto

import com.squareup.moshi.Json

data class BaseResponse(
    @field:Json(name = "status")
    val status: Int? = null,
    @field:Json(name = "description")
    val description: String? = null
)