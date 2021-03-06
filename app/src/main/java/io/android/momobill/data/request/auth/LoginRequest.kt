package io.android.momobill.data.request.auth

import com.squareup.moshi.Json

data class LoginRequest(
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "password")
    val password: String
)
