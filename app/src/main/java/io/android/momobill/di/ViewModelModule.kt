package io.android.momobill.di

import io.android.momobill.ui.login.LoginViewModel
import io.android.momobill.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { MainViewModel(getUsernameUseCase = get()) }
}