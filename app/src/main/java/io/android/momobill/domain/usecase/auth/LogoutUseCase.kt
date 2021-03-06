package io.android.momobill.domain.usecase.auth

import io.android.momobill.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {

    operator fun invoke() {
        repository.clearUserInfo()
    }
}
