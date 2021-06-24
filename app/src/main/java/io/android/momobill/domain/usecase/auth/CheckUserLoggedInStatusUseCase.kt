package io.android.momobill.domain.usecase.auth

import io.android.momobill.domain.repository.AuthRepository

class CheckUserLoggedInStatusUseCase(private val repository: AuthRepository) {

    operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}
