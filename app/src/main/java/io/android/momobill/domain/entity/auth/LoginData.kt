package io.android.momobill.domain.entity.auth

data class LoginData(
    val email: String,
    val fullName: String,
    val phone: String,
    val status: Boolean,
    val userId: String,
    val username: String
)
