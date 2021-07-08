package io.android.momobill.domain.usecase.auth

import io.android.momobill.abstraction.UseCase
import io.android.momobill.data.params.auth.LoginParams
import io.android.momobill.domain.entity.auth.LoginData
import io.android.momobill.domain.entity.auth.UserInfo
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.Either

class LoginUseCase(private val repository: AuthRepository) :
    UseCase<LoginParams, LoginData> {

    override suspend fun invoke(params: LoginParams): Either<Exception, LoginData> {
        val result = repository.login(params)
        if (result is Either.Success) {
            val userInfo = UserInfo(
                userId = result.data.userId,
                username = result.data.username,
                fullName = result.data.fullName,
                phone = result.data.phone,
                email = result.data.email
            )
            repository.saveUserInfo(userInfo)
        }
        return result
    }
}
