package io.android.momobill.di

import io.android.momobill.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(getMoviesUseCase = get()) }
}