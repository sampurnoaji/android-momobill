package io.android.momobill.di

import io.android.momobill.domain.usecase.auth.CheckUserLoggedInStatusUseCase
import io.android.momobill.domain.usecase.auth.GetUserInfoUseCase
import io.android.momobill.domain.usecase.auth.LoginUseCase
import io.android.momobill.domain.usecase.auth.LogoutUseCase
import io.android.momobill.domain.usecase.auth.RegisterUseCase
import io.android.momobill.domain.usecase.vehicle.GetVehicleDetailUseCase
import io.android.momobill.domain.usecase.vehicle.GetVehiclesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CheckUserLoggedInStatusUseCase(repository = get()) }
    factory { LoginUseCase(repository = get()) }
    factory { RegisterUseCase(repository = get()) }
    factory { GetUserInfoUseCase(repository = get()) }
    factory { LogoutUseCase(repository = get()) }

    factory { GetVehiclesUseCase(repository = get()) }
    factory { GetVehicleDetailUseCase(repository = get()) }
}
