package io.android.momobill.data.params.auth

data class RegisterParams(
    val name: String,
    val phone: String,
    val email: String,
    val password: String
)
