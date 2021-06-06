package io.android.momobill.data.source.auth

import io.android.momobill.data.dispatcher.DispatcherProvider
import io.android.momobill.data.mapper.LoginResponseMapper
import io.android.momobill.data.request.LoginRequest
import io.android.momobill.domain.entity.LoginData
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.util.mapApiResultToDomain
import io.android.momobill.vo.LoadResult

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource,
    private val dispatcher: DispatcherProvider,
    private val loginResponseMapper: LoginResponseMapper
) : AuthRepository {

    override suspend fun login(username: String, password: String): LoadResult<LoginData> {
        val request = LoginRequest(username, password)
        val apiResult = remoteDataSource.login(dispatcher.io, request)
        return apiResult.mapApiResultToDomain(loginResponseMapper)
    }

    override fun saveUserData(username: String) {
        localDataSource.saveUserData(username)
    }

    override fun getUsername(): String {
        return localDataSource.getUsername()
    }
}