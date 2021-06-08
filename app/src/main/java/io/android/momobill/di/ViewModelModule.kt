package io.android.momobill.di

import io.android.momobill.ui.account.AccountViewModel
import io.android.momobill.ui.home.HomeViewModel
import io.android.momobill.ui.login.LoginViewModel
import io.android.momobill.ui.main.MainViewModel
import io.android.momobill.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { RegisterViewModel(registerUseCase = get()) }
    viewModel { MainViewModel(getUsernameUseCase = get()) }
    viewModel { HomeViewModel() }
    viewModel { AccountViewModel() }
}