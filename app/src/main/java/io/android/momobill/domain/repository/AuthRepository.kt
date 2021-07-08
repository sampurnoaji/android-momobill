package io.android.momobill.domain.repository

import io.android.momobill.data.params.auth.LoginParams
import io.android.momobill.data.params.auth.RegisterParams
import io.android.momobill.domain.entity.auth.LoginData
import io.android.momobill.domain.entity.auth.UserInfo
import io.android.momobill.vo.Either

interface AuthRepository {
    suspend fun login(params: LoginParams): Either<Exception, LoginData>
    suspend fun register(params: RegisterParams): Either<Exception, Boolean>
    suspend fun getUserInfo(): Either<Exception, UserInfo>

    fun saveUserInfo(userInfo: UserInfo)
    fun isUserLoggedIn(): Boolean
    fun clearUserInfo()
}
