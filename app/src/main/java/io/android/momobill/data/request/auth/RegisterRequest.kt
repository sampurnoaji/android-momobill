package io.android.momobill.data.request.auth

import com.squareup.moshi.Json

data class RegisterRequest(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "password")
    val password: String,
    @field:Json(name = "phone")
    val phone: String
)
