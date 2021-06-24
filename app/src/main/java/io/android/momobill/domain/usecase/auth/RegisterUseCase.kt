package io.android.momobill.domain.usecase.auth

import io.android.momobill.abstraction.UseCase
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.LoadResult

class RegisterUseCase(private val repository: AuthRepository) : UseCase<RegisterParams, LoadResult<Boolean>>() {

    override suspend fun invoke(params: RegisterParams): LoadResult<Boolean> {
        return repository.register(params)
    }
}
