package io.android.momobill.domain.usecase.auth

import io.android.momobill.abstraction.NoParamsUseCase
import io.android.momobill.domain.entity.auth.UserInfo
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.Either

class GetUserInfoUseCase(private val repository: AuthRepository) : NoParamsUseCase<UserInfo> {

    override suspend fun invoke(): Either<Exception, UserInfo> {
        return repository.getUserInfo()
    }
}
