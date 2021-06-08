package io.android.momobill.data.source.auth

import io.android.momobill.data.dispatcher.DispatcherProvider
import io.android.momobill.data.mapper.LoginResponseMapper
import io.android.momobill.data.mapper.UserInfoResponseMapper
import io.android.momobill.data.params.LoginParams
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.data.request.LoginRequest
import io.android.momobill.data.request.RegisterRequest
import io.android.momobill.domain.entity.LoginData
import io.android.momobill.domain.entity.UserInfo
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.util.extension.mapApiResultToDomain
import io.android.momobill.util.extension.mapApiResultToSuccessOrFailure
import io.android.momobill.vo.LoadResult

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource,
    private val dispatcher: DispatcherProvider,
    private val loginResponseMapper: LoginResponseMapper,
    private val userInfoResponseMapper: UserInfoResponseMapper
) : AuthRepository {

    override suspend fun login(params: LoginParams): LoadResult<LoginData> {
        val request = LoginRequest(params.username, params.password)
        val apiResult = remoteDataSource.login(dispatcher.io, request)
        return apiResult.mapApiResultToDomain(loginResponseMapper)
    }

    override suspend fun register(params: RegisterParams): LoadResult<Boolean> {
        val request = RegisterRequest(
            name = params.name,
            phone = params.phone,
            email = params.email,
            password = params.password
        )
        val apiResult = remoteDataSource.register(dispatcher.io, request)
        return apiResult.mapApiResultToSuccessOrFailure()
    }

    override suspend fun getUserInfo(): LoadResult<UserInfo> {
        val apiResult = remoteDataSource.getUserInfo(dispatcher.io)
        return apiResult.mapApiResultToDomain(userInfoResponseMapper)
    }

    override fun saveUserInfo(userInfo: UserInfo) {
        localDataSource.saveUserInfo(userInfo)
    }

    override fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }
}