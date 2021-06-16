package io.android.momobill.data.service

import io.android.momobill.data.dto.BaseResponse
import io.android.momobill.data.dto.auth.LoginResponse
import io.android.momobill.data.dto.auth.UserInfoResponse
import io.android.momobill.data.request.LoginRequest
import io.android.momobill.data.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("mock:true")
    @POST("auth/mobile/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @Headers("mock:true")
    @POST("auth/mobile/register")
    suspend fun register(@Body request: RegisterRequest): BaseResponse

    @Headers("mock:true")
    @GET("auth/mobile/user")
    suspend fun getUserInfo(): UserInfoResponse
}
