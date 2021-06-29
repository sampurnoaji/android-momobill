package io.android.momobill.di

import io.android.momobill.ui.account.AccountViewModel
import io.android.momobill.ui.home.HomeViewModel
import io.android.momobill.ui.login.LoginViewModel
import io.android.momobill.ui.register.RegisterViewModel
import io.android.momobill.ui.splash.SplashViewModel
import io.android.momobill.ui.vehicle.detail.VehicleDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(checkUserLoggedInStatusUseCase = get()) }
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { RegisterViewModel(registerUseCase = get()) }
    viewModel { HomeViewModel(getVehiclesUseCase = get()) }
    viewModel { AccountViewModel(getUserInfoUseCase = get(), logoutUseCase = get()) }

    viewModel { VehicleDetailViewModel(getVehicleDetailUseCase = get()) }
}
