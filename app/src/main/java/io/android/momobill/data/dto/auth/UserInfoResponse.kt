package io.android.momobill.data.dto.auth

import com.squareup.moshi.Json

data class UserInfoResponse(
    @field:Json(name = "email")
    val email: String? = null,
    @field:Json(name = "full-name")
    val fullName: String? = null,
    @field:Json(name = "phone")
    val phone: String? = null,
    @field:Json(name = "user-id")
    val userId: String? = null,
    @field:Json(name = "username")
    val username: String? = null
)
