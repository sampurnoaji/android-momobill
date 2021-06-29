package io.android.momobill.data.source.auth

import io.android.momobill.data.mapper.auth.LoginResponseMapper
import io.android.momobill.data.mapper.auth.UserInfoResponseMapper
import io.android.momobill.data.params.LoginParams
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.data.request.LoginRequest
import io.android.momobill.data.request.RegisterRequest
import io.android.momobill.domain.entity.auth.LoginData
import io.android.momobill.domain.entity.auth.UserInfo
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.Either

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource,
    private val loginResponseMapper: LoginResponseMapper,
    private val userInfoResponseMapper: UserInfoResponseMapper
) : AuthRepository {

    override suspend fun login(params: LoginParams): Either<Exception, LoginData> {
        val request = LoginRequest(params.username, params.password)
        return when (val result = remoteDataSource.login(request)) {
            is Either.Success -> Either.Success(loginResponseMapper(result.data))
            is Either.Failure -> Either.Failure(result.cause)
        }
    }

    override suspend fun register(params: RegisterParams): Either<Exception, Boolean> {
        val request = RegisterRequest(
            name = params.name,
            phone = params.phone,
            email = params.email,
            password = params.password
        )
        return when (val result = remoteDataSource.register(request)) {
            is Either.Success -> Either.Success(result.data.status == 200)
            is Either.Failure -> Either.Failure(result.cause)
        }
    }

    override suspend fun getUserInfo(): Either<Exception, UserInfo> {
        return when (val result = remoteDataSource.getUserInfo()) {
            is Either.Success -> Either.Success(userInfoResponseMapper(result.data))
            is Either.Failure -> Either.Failure(result.cause)
        }
    }

    override fun saveUserInfo(userInfo: UserInfo) {
        localDataSource.saveUserInfo(userInfo)
    }

    override fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

    override fun clearUserInfo() {
        localDataSource.clearUserInfo()
    }
}
