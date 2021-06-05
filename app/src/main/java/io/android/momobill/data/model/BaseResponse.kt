package io.android.momobill.data.model

import com.squareup.moshi.Json

data class BaseResponse(
    @field:Json(name = "code")
    val code: Int? = null,
    @field:Json(name = "message")
    val message: String? = null,
    @field:Json(name = "success")
    val success: Boolean? = null
)