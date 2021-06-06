package io.android.momobill.domain.usecase

import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.entity.LoginData
import io.android.momobill.domain.entity.LoginParams
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.LoadResult

class LoginUseCase(private val repository: AuthRepository) :
    UseCase<LoginParams, LoadResult<LoginData>>() {

    override suspend fun invoke(params: LoginParams): LoadResult<LoginData> {
        val result = repository.login(params.username, params.password)
        if (result is LoadResult.Success) {
            repository.saveUserData(result.data.username)
        }
        return result
    }
}