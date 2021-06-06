package io.android.momobill.data.service

import io.android.momobill.data.dto.auth.LoginResponse
import io.android.momobill.data.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("mock:true")
    @POST("auth/mobile/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}