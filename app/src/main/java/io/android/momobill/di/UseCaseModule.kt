package io.android.momobill.di

import io.android.momobill.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(repository = get()) }
}