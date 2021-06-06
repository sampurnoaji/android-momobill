package io.android.momobill.data.source.auth

import io.android.momobill.data.dto.auth.LoginResponse
import io.android.momobill.data.request.LoginRequest
import io.android.momobill.data.service.AuthService
import io.android.momobill.data.source.RemoteDataSource
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.CoroutineDispatcher

class AuthRemoteDataSource(private val service: AuthService) : RemoteDataSource() {

    suspend fun login(
        dispatcher: CoroutineDispatcher,
        request: LoginRequest
    ): LoadResult<LoginResponse> {
        return call(dispatcher) { service.login(request) }
    }
}