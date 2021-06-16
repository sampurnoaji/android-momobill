package io.android.momobill.di

import io.android.momobill.domain.usecase.CheckUserLoggedInStatusUseCase
import io.android.momobill.domain.usecase.GetUserInfoUseCase
import io.android.momobill.domain.usecase.LoginUseCase
import io.android.momobill.domain.usecase.LogoutUseCase
import io.android.momobill.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CheckUserLoggedInStatusUseCase(repository = get()) }
    factory { LoginUseCase(repository = get()) }
    factory { RegisterUseCase(repository = get()) }
    factory { GetUserInfoUseCase(repository = get()) }
    factory { LogoutUseCase(repository = get()) }
}
