package io.android.momobill.domain.usecase.auth

import io.android.momobill.abstraction.UseCase
import io.android.momobill.data.params.auth.RegisterParams
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.Either

class RegisterUseCase(private val repository: AuthRepository) : UseCase<RegisterParams, Boolean> {

    override suspend fun invoke(params: RegisterParams): Either<Exception, Boolean> {
        return repository.register(params)
    }
}
