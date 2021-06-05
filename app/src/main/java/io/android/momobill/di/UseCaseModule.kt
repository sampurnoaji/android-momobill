package io.android.momobill.di

import io.android.momobill.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMoviesUseCase(repository = get()) }
}