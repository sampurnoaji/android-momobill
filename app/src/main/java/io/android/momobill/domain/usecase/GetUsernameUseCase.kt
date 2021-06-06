package io.android.momobill.domain.usecase

import io.android.momobill.domain.repository.AuthRepository

class GetUsernameUseCase(private val repository: AuthRepository) {
    operator fun invoke(): String {
        return repository.getUsername()
    }
}