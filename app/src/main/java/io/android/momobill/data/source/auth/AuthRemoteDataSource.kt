package io.android.momobill.data.source.auth

import io.android.momobill.data.dto.BaseResponse
import io.android.momobill.data.dto.auth.LoginResponse
import io.android.momobill.data.dto.auth.UserInfoResponse
import io.android.momobill.data.request.auth.LoginRequest
import io.android.momobill.data.request.auth.RegisterRequest
import io.android.momobill.data.service.AuthService
import io.android.momobill.data.util.ApiClient
import io.android.momobill.vo.ApiResponse
import io.android.momobill.vo.Either

class AuthRemoteDataSource(private val service: AuthService) : ApiClient() {

    suspend fun login(request: LoginRequest): Either<Exception, LoginResponse> {
        return when (val response = call { service.login(request) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun register(request: RegisterRequest): Either<Exception, BaseResponse> {
        return when (val response = call { service.register(request) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun getUserInfo(): Either<Exception, UserInfoResponse> {
        return when (val response = call { service.getUserInfo() }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}
